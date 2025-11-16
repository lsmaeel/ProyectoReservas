package com.example.ProyectoReservas.servicios;

import com.example.ProyectoReservas.DTO.*;
import com.example.ProyectoReservas.entities.*;
import com.example.ProyectoReservas.repositories.*;
import com.example.ProyectoReservas.DTO.requests.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AulaService {
    private final AulaRepository aulaRepository;

    public AulaService(AulaRepository aulaRepository) {
        this.aulaRepository = aulaRepository;
    }

    public List<AulaDTO> findAll() {
        return aulaRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public AulaDTO findById(Long id) {
        Aula aula = aulaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Aula no encontrada"));
        return toDTO(aula);
    }

    public AulaDTO create(AulaRequest request) {
        Aula aula = Aula.builder()
                .nombre(request.getNombre())
                .capacidad(request.getCapacidad())
                .esAulaDeOrdenadores(request.getEsAulaDeOrdenadores())
                .numeroOrdenadores(request.getNumeroOrdenadores())
                .build();

        return toDTO(aulaRepository.save(aula));
    }

    public AulaDTO update(Long id, AulaRequest request) {
        Aula aula = aulaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Aula no encontrada"));

        aula.setNombre(request.getNombre());
        aula.setCapacidad(request.getCapacidad());
        aula.setEsAulaDeOrdenadores(request.getEsAulaDeOrdenadores());
        aula.setNumeroOrdenadores(request.getNumeroOrdenadores());

        return toDTO(aulaRepository.save(aula));
    }

    public void delete(Long id) {
        aulaRepository.deleteById(id);
    }

    public List<AulaDTO> findByCapacidad(Integer capacidad) {
        return aulaRepository.findByCapacidadGreaterThan(capacidad).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public List<AulaDTO> findConOrdenadores() {
        return aulaRepository.findByEsAulaDeOrdenadoresTrue().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public Aula getAula(Long id) {
        return aulaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Aula no encontrada"));
    }

    public AulaDTO toDTO(Aula aula) {
        return AulaDTO.builder()
                .id(aula.getId())
                .nombre(aula.getNombre())
                .capacidad(aula.getCapacidad())
                .esAulaDeOrdenadores(aula.getEsAulaDeOrdenadores())
                .numeroOrdenadores(aula.getNumeroOrdenadores())
                .build();
    }
}