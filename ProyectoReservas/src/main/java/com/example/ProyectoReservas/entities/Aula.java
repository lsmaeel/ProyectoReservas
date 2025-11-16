
package com.example.ProyectoReservas.entities;

import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Aula {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private Integer capacidad;
    private Boolean esAulaDeOrdenadores;
    private Integer numeroOrdenadores;

    @OneToMany(mappedBy = "aula", cascade = CascadeType.ALL)
    private List<Reserva> reservas = new ArrayList<>();
}