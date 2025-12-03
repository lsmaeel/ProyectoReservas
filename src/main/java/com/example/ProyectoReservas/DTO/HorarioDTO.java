package com.example.ProyectoReservas.DTO;

import lombok.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HorarioDTO {
    private Long id;
    private String diaSemana;
    private Integer sesionDia;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private String tipo;
}
