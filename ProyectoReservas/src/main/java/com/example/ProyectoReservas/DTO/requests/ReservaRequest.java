package com.example.ProyectoReservas.DTO.requests;
import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservaRequest {
    @NotNull(message = "La fecha es obligatoria")
    @Future(message = "La fecha debe ser futura")
    private LocalDate fecha;

    @NotBlank(message = "El motivo es obligatorio")
    private String motivo;

    @NotNull(message = "El número de asistentes es obligatorio")
    @Min(value = 1, message = "Debe haber al menos 1 asistente")
    private Integer numeroAsistentes;

    @NotNull(message = "El aula es obligatoria")
    private Long aulaId;

    @NotNull(message = "El tramo horario es obligatorio")
    private Long tramoHorarioId;
}

