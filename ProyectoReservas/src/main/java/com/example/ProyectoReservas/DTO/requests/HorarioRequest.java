package com.example.ProyectoReservas.DTO.requests;

import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HorarioRequest {
    @NotNull(message = "El día de la semana es obligatorio")
    private String diaSemana;

    @NotNull(message = "La sesión del día es obligatoria")
    @Min(value = 1, message = "La sesión debe ser mayor a 0")
    private Integer sesionDia;

    @NotNull(message = "La hora de inicio es obligatoria")
    private LocalTime horaInicio;

    @NotNull(message = "La hora de fin es obligatoria")
    private LocalTime horaFin;

    @NotNull(message = "El tipo es obligatorio")
    private String tipo;
}
