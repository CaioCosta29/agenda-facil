package com.agendafacil.agendafacil_api.adapters.out.repository.agendamento;

import com.agendafacil.agendafacil_api.adapters.out.repository.agendamento.mapper.AgendamentoEntityMapper;
import com.agendafacil.agendafacil_api.application.core.domain.Agendamento;
import com.agendafacil.agendafacil_api.application.ports.out.agendamento.FazerAgendamentoOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FazerAgendamentoAdapter implements FazerAgendamentoOutputPort {

    private final AgendamentoRepository agendamentoRepository;
    private final AgendamentoEntityMapper agendamentoEntityMapper;

    @Override
    public Agendamento agendar(Agendamento agendamento) {
        var agendamentoEntity = agendamentoEntityMapper.toEntity(agendamento);
        var agendamentoSalvo = agendamentoRepository.save(agendamentoEntity);
        return agendamentoEntityMapper.toDomain(agendamentoSalvo);
    }
}
