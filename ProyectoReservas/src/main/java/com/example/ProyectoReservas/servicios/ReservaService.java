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
public class ReservaService {
    private final ReservaRepository reservaRepository;
    private final AulaService aulaService;
    private final HorarioService tramoHorarioService;
    private final UsuarioRepository usuarioRepository;

    public ReservaService(ReservaRepository reservaRepository, AulaService aulaService,
                          HorarioService tramoHorarioService, UsuarioRepository usuarioRepository) {
        this.reservaRepository = reservaRepository;
        this.aulaService = aulaService;
        this.tramoHorarioService = tramoHorarioService;
        this.usuarioRepository = usuarioRepository;
    }

    public List<ReservaDTO> findAll() {
        return reservaRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public ReservaDTO findById(Long id) {
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada"));
        return toDTO(reserva);
    }

    public ReservaDTO create(ReservaRequest request, String email) {
        Aula aula = aulaService.getAula(request.getAulaId());
        Horario tramoHorario = tramoHorarioService.getTramoHorario(request.getTramoHorarioId());
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (request.getFecha().isBefore(LocalDate.now())) {
            throw new RuntimeException("No se permiten reservas en el pasado");
        }

        if (request.getNumeroAsistentes() > aula.getCapacidad()) {
            throw new RuntimeException("El número de asistentes supera la capacidad del aula");
        }

        if (reservaRepository.existsByAulaIdAndTramoHorarioIdAndFecha(
                request.getAulaId(), request.getTramoHorarioId(), request.getFecha())) {
            throw new RuntimeException("Ya existe una reserva para este aula, fecha y tramo horario");
        }

        Reserva reserva = Reserva.builder()
                .fecha(request.getFecha())
                .motivo(request.getMotivo())
                .numeroAsistentes(request.getNumeroAsistentes())
                .aula(aula)
                .tramoHorario(tramoHorario)
                .usuario(usuario)
                .build();

        return toDTO(reservaRepository.save(reserva));
    }

    public void delete(Long id, String email, String role) {
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada"));

        if (!role.equals("ROLE_ADMIN") && !reserva.getUsuario().getEmail().equals(email)) {
            throw new RuntimeException("No tienes permiso para eliminar esta reserva");
        }

        reservaRepository.deleteById(id);
    }

    public List<ReservaDTO> findByAulaId(Long aulaId) {
        return reservaRepository.findByAulaId(aulaId).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public ReservaDTO toDTO(Reserva reserva) {
        return ReservaDTO.builder()
                .id(reserva.getId())
                .fecha(reserva.getFecha())
                .motivo(reserva.getMotivo())
                .numeroAsistentes(reserva.getNumeroAsistentes())
                .fechaCreacion(reserva.getFechaCreacion())
                .aula(aulaService.toDTO(reserva.getAula()))
                .tramoHorario(tramoHorarioService.toDTO(reserva.getTramoHorario()))
                .usuarioNombre(reserva.getUsuario().getNombre())
                .build();
    }
}