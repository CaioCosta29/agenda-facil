package com.agendafacil.agendafacil_api.adapters.out.repository.agendamento;

import com.agendafacil.agendafacil_api.adapters.out.repository.agendamento.mapper.AgendamentoEntityMapper;
import com.agendafacil.agendafacil_api.application.core.domain.Agendamento;
import com.agendafacil.agendafacil_api.application.core.exception.AgendamentoNaoEncontradoException;
import com.agendafacil.agendafacil_api.application.ports.out.agendamento.ObterAgendamentoPeloIdOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ObterAgendamentoPeloIdAdapter implements ObterAgendamentoPeloIdOutputPort {

    private final AgendamentoRepository agendamentoRepository;
    private final AgendamentoEntityMapper agendamentoEntityMapper;

    @Override
    public Agendamento obter(Long id) {
        return agendamentoRepository.findById(id)
                .map(agendamentoEntityMapper::toDomain)
                .orElseThrow(() -> new AgendamentoNaoEncontradoException(id));
    }
}
