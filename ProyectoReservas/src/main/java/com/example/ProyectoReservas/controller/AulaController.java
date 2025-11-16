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
@RequestMapping("/aulas")
@CrossOrigin(origins = "*")
class AulaController {
    private final AulaService aulaService;
    private final ReservaService reservaService;

    public AulaController(AulaService aulaService, ReservaService reservaService) {
        this.aulaService = aulaService;
        this.reservaService = reservaService;
    }

    @GetMapping
    public ResponseEntity<List<AulaDTO>> getAulas(
            @RequestParam(required = false) Integer capacidad,
            @RequestParam(required = false) Boolean ordenadores) {

        if (capacidad != null) {
            return ResponseEntity.ok(aulaService.findByCapacidad(capacidad));
        }
        if (ordenadores != null && ordenadores) {
            return ResponseEntity.ok(aulaService.findConOrdenadores());
        }
        return ResponseEntity.ok(aulaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AulaDTO> getAula(@PathVariable Long id) {
        return ResponseEntity.ok(aulaService.findById(id));
    }

    @PostMapping
    public ResponseEntity<?> createAula(@Valid @RequestBody AulaRequest request) {
        try {
            return ResponseEntity.ok(aulaService.create(request));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ErrorResponseDTO.builder()
                    .mensaje(e.getMessage())
                    .status(400)
                    .timestamp(LocalDate.now())
                    .build());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAula(@PathVariable Long id, @Valid @RequestBody AulaRequest request) {
        try {
            return ResponseEntity.ok(aulaService.update(id, request));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ErrorResponseDTO.builder()
                    .mensaje(e.getMessage())
                    .status(400)
                    .timestamp(LocalDate.now())
                    .build());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAula(@PathVariable Long id) {
        try {
            aulaService.delete(id);
            return ResponseEntity.ok().body(java.util.Map.of("mensaje", "Aula eliminada correctamente"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ErrorResponseDTO.builder()
                    .mensaje(e.getMessage())
                    .status(400)
                    .timestamp(LocalDate.now())
                    .build());
        }
    }

    @GetMapping("/{id}/reservas")
    public ResponseEntity<List<ReservaDTO>> getReservasAula(@PathVariable Long id) {
        return ResponseEntity.ok(reservaService.findByAulaId(id));
    }
}
