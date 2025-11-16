package com.example.ProyectoReservas.DTO;

import lombok.*;
import java.time.LocalDate;
import java.time.LocalTime;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {
    private Long id;
    private String nombre;
    private String email;
    private String role;
}