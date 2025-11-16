package com.example.ProyectoReservas.entities;

import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "horarios")
public class Horario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private DiaSemana diaSemana;

    private Integer sesionDia;
    private java.time.LocalTime horaInicio;
    private java.time.LocalTime horaFin;

    @Enumerated(EnumType.STRING)
    private TipoTramo tipo;

    @OneToMany(mappedBy = "tramoHorario", cascade = CascadeType.ALL)
    private List<Reserva> reservas = new ArrayList<>();
}


