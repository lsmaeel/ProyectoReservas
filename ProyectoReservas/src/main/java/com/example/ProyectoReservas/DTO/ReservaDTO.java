package com.example.ProyectoReservas.DTO;

import lombok.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReservaDTO {
    private Long id;
    private LocalDate fecha;
    private String motivo;
    private Integer numeroAsistentes;
    private LocalDate fechaCreacion;
    private AulaDTO aula;
    private HorarioDTO tramoHorario;
    private String usuarioNombre;
}