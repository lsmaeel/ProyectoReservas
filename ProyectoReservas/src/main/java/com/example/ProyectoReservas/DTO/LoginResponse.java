package com.example.ProyectoReservas.DTO;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {
    private String token;
    private String nombre;
    private String email;
    private String role;
}
