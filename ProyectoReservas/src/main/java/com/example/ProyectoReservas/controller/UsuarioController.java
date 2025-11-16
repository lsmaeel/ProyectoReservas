package com.example.ProyectoReservas.controller;

import com.example.ProyectoReservas.DTO.ErrorResponseDTO;
import com.example.ProyectoReservas.DTO.UsuarioDTO;
import com.example.ProyectoReservas.DTO.requests.ActualizarUsuarioRequest;
import com.example.ProyectoReservas.DTO.requests.CambiarContraRequest;
import com.example.ProyectoReservas.entities.Usuario;
import com.example.ProyectoReservas.servicios.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.util.Map;

@RestController
@RequestMapping("/usuario")
@CrossOrigin(origins = "*")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUsuario(@PathVariable Long id) {
        try {
            usuarioService.deleteUsuario(id);
            return ResponseEntity.ok(Map.of("mensaje", "Usuario eliminado correctamente"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                    ErrorResponseDTO.builder()
                            .mensaje(e.getMessage())
                            .status(400)
                            .timestamp(LocalDate.now())
                            .build()
            );
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUsuario(@PathVariable Long id, @Valid @RequestBody ActualizarUsuarioRequest request) {
        try {
            Usuario usuario = usuarioService.updateUsuario(id, request);
            return ResponseEntity.ok(usuarioService.toDTO(usuario));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                    ErrorResponseDTO.builder()
                            .mensaje(e.getMessage())
                            .status(400)
                            .timestamp(LocalDate.now())
                            .build()
            );
        }
    }

    @PatchMapping("/cambiar-pass")
    public ResponseEntity<?> changePassword(@Valid @RequestBody CambiarContraRequest request, Principal principal) {
        try {
            Usuario usuario = usuarioService.loadUserByEmail(principal.getName());
            usuarioService.changePassword(usuario.getId(), request);
            return ResponseEntity.ok(Map.of("mensaje", "Contraseña cambiada correctamente"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                    ErrorResponseDTO.builder()
                            .mensaje(e.getMessage())
                            .status(400)
                            .timestamp(LocalDate.now())
                            .build()
            );
        }
    }
}
