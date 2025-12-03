package com.example.ProyectoReservas.DTO;

import lombok.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AulaDTO {
    private Long id;
    private String nombre;
    private Integer capacidad;
    private Boolean esAulaDeOrdenadores;
    private Integer numeroOrdenadores;
}