package com.example.ProyectoReservas.servicios;

import com.example.ProyectoReservas.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        System.out.println("CustomUserDetailsService - Buscando usuario: " + email);

        return usuarioRepository.findByEmail(email)
                .orElseThrow(() -> {
                    System.out.println("Usuario NO encontrado: " + email);
                    return new UsernameNotFoundException("Usuario no encontrado con email: " + email);
                });
    }
}