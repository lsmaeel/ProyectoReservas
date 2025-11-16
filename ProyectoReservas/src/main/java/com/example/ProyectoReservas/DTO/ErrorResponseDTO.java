package com.example.ProyectoReservas.DTO;

import lombok.*;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponseDTO {
    private String mensaje;
    private Integer status;
    private LocalDate timestamp;
}
