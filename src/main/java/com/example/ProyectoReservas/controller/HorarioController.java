package com.example.ProyectoReservas.controller;

import com.example.ProyectoReservas.DTO.*;
import com.example.ProyectoReservas.entities.*;
import com.example.ProyectoReservas.DTO.requests.*;
import com.example.ProyectoReservas.servicios.*;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.time.LocalDate;
import java.util.List;
@RestController
@RequestMapping("/tramo-horario")
@CrossOrigin(origins = "*")
class HorarioController {
    private final HorarioService horarioService;

    public HorarioController(HorarioService horarioService) {
        this.horarioService = horarioService;
    }

    @GetMapping
    public ResponseEntity<List<HorarioDTO>> getTramosHorarios() {
        return ResponseEntity.ok(horarioService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<HorarioDTO> getTramoHorario(@PathVariable Long id) {
        return ResponseEntity.ok(horarioService.findById(id));
    }

    @PostMapping
    public ResponseEntity<?> createTramoHorario(@Valid @RequestBody HorarioRequest request) {
        try {
            return ResponseEntity.ok(horarioService.create(request));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ErrorResponseDTO.builder()
                    .mensaje(e.getMessage())
                    .status(400)
                    .timestamp(LocalDate.now())
                    .build());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTramoHorario(@PathVariable Long id) {
        try {
            horarioService.delete(id);
            return ResponseEntity.ok().body(java.util.Map.of("mensaje", "Tramo horario eliminado correctamente"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ErrorResponseDTO.builder()
                    .mensaje(e.getMessage())
                    .status(400)
                    .timestamp(LocalDate.now())
                    .build());
        }
    }
}