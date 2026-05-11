package com.agendafacil.agendafacil_api.adapters.out.repository.agendamento;

import com.agendafacil.agendafacil_api.application.ports.out.agendamento.VerificarSeExisteAgendamentoParaMesmoHorarioOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class VerificarSeExisteAgendamentoParaMesmoHorariAdapter implements VerificarSeExisteAgendamentoParaMesmoHorarioOutputPort {

    private final AgendamentoRepository agendamentoRepository;

    @Override
    public Boolean verificar(Long idProfissional, LocalDateTime dataHora) {
        return agendamentoRepository.existsByProfissionalIdAndDataHora(idProfissional, dataHora);
    }
}
