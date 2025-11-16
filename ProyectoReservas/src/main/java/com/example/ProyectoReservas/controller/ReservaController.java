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
@RequestMapping("/reservas")
@CrossOrigin(origins = "*")
class ReservaController {
    private final ReservaService reservaService;

    public ReservaController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }

    @GetMapping
    public ResponseEntity<List<ReservaDTO>> getReservas() {
        return ResponseEntity.ok(reservaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservaDTO> getReserva(@PathVariable Long id) {
        return ResponseEntity.ok(reservaService.findById(id));
    }

    @PostMapping
    public ResponseEntity<?> createReserva(@Valid @RequestBody ReservaRequest request, Principal principal) {
        try {
            return ResponseEntity.ok(reservaService.create(request, principal.getName()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ErrorResponseDTO.builder()
                    .mensaje(e.getMessage())
                    .status(400)
                    .timestamp(LocalDate.now())
                    .build());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteReserva(@PathVariable Long id, Principal principal) {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String role = auth.getAuthorities().iterator().next().getAuthority();
            reservaService.delete(id, principal.getName(), role);
            return ResponseEntity.ok().body(java.util.Map.of("mensaje", "Reserva eliminada correctamente"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ErrorResponseDTO.builder()
                    .mensaje(e.getMessage())
                    .status(400)
                    .timestamp(LocalDate.now())
                    .build());
        }
    }
}