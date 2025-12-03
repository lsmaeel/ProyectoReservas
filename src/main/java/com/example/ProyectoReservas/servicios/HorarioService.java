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
public class HorarioService {
    private final HorarioRepository horarioRepository;

    public HorarioService(HorarioRepository horarioRepository) {
        this.horarioRepository = horarioRepository;
    }

    public List<HorarioDTO> findAll() {
        return horarioRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public HorarioDTO findById(Long id) {
        Horario tramo = horarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tramo horario no encontrado"));
        return toDTO(tramo);
    }

    public HorarioDTO create(HorarioRequest request) {
        Horario tramo = Horario.builder()
                .diaSemana(DiaSemana.valueOf(request.getDiaSemana()))
                .sesionDia(request.getSesionDia())
                .horaInicio(request.getHoraInicio())
                .horaFin(request.getHoraFin())
                .tipo(TipoTramo.valueOf(request.getTipo()))
                .build();

        return toDTO(horarioRepository.save(tramo));
    }

    public void delete(Long id) {
        horarioRepository.deleteById(id);
    }

    public Horario getTramoHorario(Long id) {
        return horarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tramo horario no encontrado"));
    }

    public HorarioDTO toDTO(Horario tramo) {
        return HorarioDTO.builder()
                .id(tramo.getId())
                .diaSemana(tramo.getDiaSemana().name())
                .sesionDia(tramo.getSesionDia())
                .horaInicio(tramo.getHoraInicio())
                .horaFin(tramo.getHoraFin())
                .tipo(tramo.getTipo().name())
                .build();
    }
}