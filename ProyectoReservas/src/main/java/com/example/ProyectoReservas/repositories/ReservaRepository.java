package com.example.ProyectoReservas.repositories;



import com.example.ProyectoReservas.entities.Aula;
import com.example.ProyectoReservas.entities.Horario;
import com.example.ProyectoReservas.entities.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    List<Reserva> findByAulaId(Long aulaId);
    boolean existsByAulaIdAndTramoHorarioIdAndFecha(Long aulaId, Long tramoHorarioId, LocalDate fecha);
    List<Reserva> findByUsuarioId(Long usuarioId);
}