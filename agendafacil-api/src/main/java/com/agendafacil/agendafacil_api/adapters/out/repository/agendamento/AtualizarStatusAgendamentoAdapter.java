package com.agendafacil.agendafacil_api.adapters.out.repository.agendamento;

import com.agendafacil.agendafacil_api.adapters.out.repository.agendamento.mapper.AgendamentoEntityMapper;
import com.agendafacil.agendafacil_api.application.core.domain.Agendamento;
import com.agendafacil.agendafacil_api.application.ports.out.agendamento.AtualizarStatusAgendamentoOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AtualizarStatusAgendamentoAdapter implements AtualizarStatusAgendamentoOutputPort {

    private final AgendamentoRepository agendamentoRepository;
    private final AgendamentoEntityMapper agendamentoEntityMapper;

    @Override
    public Agendamento atualizar(Agendamento agendamento) {
        var agendamentoAtualizado = agendamentoRepository.save(agendamentoEntityMapper.toEntity(agendamento));

        return agendamentoEntityMapper.toDomain(agendamentoAtualizado);
    }
}
