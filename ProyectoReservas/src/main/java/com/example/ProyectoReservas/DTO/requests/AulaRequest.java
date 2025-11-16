package com.example.ProyectoReservas.DTO.requests;

import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AulaRequest {
    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotNull(message = "La capacidad es obligatoria")
    @Min(value = 1, message = "La capacidad debe ser mayor a 0")
    private Integer capacidad;

    @NotNull(message = "Debe indicar si es aula de ordenadores")
    private Boolean esAulaDeOrdenadores;

    @Min(value = 0, message = "El número de ordenadores no puede ser negativo")
    private Integer numeroOrdenadores;
}