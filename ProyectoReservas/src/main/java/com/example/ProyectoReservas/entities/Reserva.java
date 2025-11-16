package com.example.ProyectoReservas.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "reservas")
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate fecha;
    private String motivo;
    private Integer numeroAsistentes;

    @CreationTimestamp
    private LocalDate fechaCreacion;

    @ManyToOne
    @JoinColumn(name = "aula_id")
    private Aula aula;

    @ManyToOne
    @JoinColumn(name = "tramo_horario_id")
    private Horario tramoHorario;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
}