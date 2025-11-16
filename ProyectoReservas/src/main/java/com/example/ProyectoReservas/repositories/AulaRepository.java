package com.example.ProyectoReservas.repositories;

import com.example.ProyectoReservas.entities.Aula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface AulaRepository extends JpaRepository<Aula, Long> {
    List<Aula> findByCapacidadGreaterThan(Integer capacidad);
    List<Aula> findByEsAulaDeOrdenadoresTrue();
}