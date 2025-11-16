package com.example.ProyectoReservas.controller;

import com.example.ProyectoReservas.DTO.ErrorResponseDTO;
import com.example.ProyectoReservas.DTO.LoginResponse;
import com.example.ProyectoReservas.DTO.UsuarioDTO;
import com.example.ProyectoReservas.DTO.requests.LoginRequest;
import com.example.ProyectoReservas.DTO.requests.RegisterRequest;
import com.example.ProyectoReservas.entities.Usuario;
import com.example.ProyectoReservas.servicios.JwtService;
import com.example.ProyectoReservas.servicios.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final UsuarioService usuarioService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthController(UsuarioService usuarioService, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.usuarioService = usuarioService;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest request) {
        try {
            Usuario usuario = usuarioService.register(request);
            return ResponseEntity.ok(usuarioService.toDTO(usuario));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ErrorResponseDTO.builder()
                    .mensaje(e.getMessage())
                    .status(400)
                    .timestamp(LocalDate.now())
                    .build());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );

            Usuario usuario = usuarioService.findUsuarioByEmail(request.getEmail())
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

            String token = jwtService.generateToken(usuario);

            return ResponseEntity.ok(LoginResponse.builder()
                    .token(token)
                    .nombre(usuario.getNombre())
                    .email(usuario.getEmail())
                    .role(usuario.getRole().name())
                    .build());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ErrorResponseDTO.builder()
                    .mensaje("Credenciales incorrectas")
                    .status(400)
                    .timestamp(LocalDate.now())
                    .build());
        }
    }

    @GetMapping("/perfil")
    public ResponseEntity<UsuarioDTO> getPerfil(Principal principal) {
        Usuario usuario = usuarioService.findUsuarioByEmail(principal.getName())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return ResponseEntity.ok(usuarioService.toDTO(usuario));
    }
}
