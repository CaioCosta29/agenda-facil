package com.agendafacil.agendafacil_api.adapters.out.repository.agendamento;

import com.agendafacil.agendafacil_api.adapters.out.repository.agendamento.mapper.AgendamentoEntityMapper;
import com.agendafacil.agendafacil_api.application.core.domain.Agendamento;
import com.agendafacil.agendafacil_api.application.ports.out.agendamento.ObterAgendamentosOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ObterAgendamentosAdapter implements ObterAgendamentosOutputPort {

    private final AgendamentoRepository agendamentoRepository;
    private final AgendamentoEntityMapper agendamentoEntityMapper;

    @Override
    public List<Agendamento> obter() {
        var agendamentosEntity = agendamentoRepository.findAll();

        return agendamentosEntity.stream().map(agendamentoEntityMapper::toDomain).toList();
    }
}
