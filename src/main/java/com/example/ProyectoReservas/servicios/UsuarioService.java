package com.example.ProyectoReservas.servicios;

import com.example.ProyectoReservas.DTO.UsuarioDTO;
import com.example.ProyectoReservas.DTO.requests.CambiarContraRequest;
import com.example.ProyectoReservas.DTO.requests.ActualizarUsuarioRequest;
import com.example.ProyectoReservas.DTO.requests.RegisterRequest;
import com.example.ProyectoReservas.entities.Role;
import com.example.ProyectoReservas.entities.Usuario;
import com.example.ProyectoReservas.repositories.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Usuario register(RegisterRequest request) {
        if (usuarioRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("El email ya está registrado");
        }
        Usuario usuario = Usuario.builder()
                .nombre(request.getNombre())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.valueOf(request.getRole()))
                .build();
        return usuarioRepository.save(usuario);
    }

    public UsuarioDTO toDTO(Usuario usuario) {
        return UsuarioDTO.builder()
                .id(usuario.getId())
                .nombre(usuario.getNombre())
                .email(usuario.getEmail())
                .role(usuario.getRole().name())
                .build();
    }

    public void deleteUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }

    public Usuario updateUsuario(Long id, ActualizarUsuarioRequest request) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        usuario.setNombre(request.getNombre());
        usuario.setEmail(request.getEmail());
        return usuarioRepository.save(usuario);
    }

    public void changePassword(Long id, CambiarContraRequest request) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        if (!passwordEncoder.matches(request.getCurrentPassword(), usuario.getPassword())) {
            throw new RuntimeException("Contraseña actual incorrecta");
        }
        usuario.setPassword(passwordEncoder.encode(request.getNewPassword()));
        usuarioRepository.save(usuario);
    }

    public Optional<Usuario> findUsuarioByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    public Usuario loadUserByEmail(String email) {
        return usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }
}