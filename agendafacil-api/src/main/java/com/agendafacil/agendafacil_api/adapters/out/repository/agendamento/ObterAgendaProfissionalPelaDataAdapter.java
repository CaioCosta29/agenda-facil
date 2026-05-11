package com.agendafacil.agendafacil_api.adapters.out.repository.agendamento;

import com.agendafacil.agendafacil_api.adapters.out.repository.agendamento.mapper.AgendamentoEntityMapper;
import com.agendafacil.agendafacil_api.application.core.domain.Agendamento;
import com.agendafacil.agendafacil_api.application.ports.out.agendamento.ObterAgendaProfissionalPelaDataOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Component
public class ObterAgendaProfissionalPelaDataAdapter implements ObterAgendaProfissionalPelaDataOutputPort {

    private final AgendamentoRepository agendamentoRepository;
    private final AgendamentoEntityMapper agendamentoEntityMapper;

    @Override
    public List<Agendamento> obter(Long id, LocalDate data) {
        LocalDateTime inicio = data.atStartOfDay();
        LocalDateTime fim = data.atTime(23, 59, 59);

        return agendamentoRepository.findByProfissionalIdAndDataHoraBetween(id, inicio, fim)
                .stream()
                .map(agendamentoEntityMapper::toDomain)
                .toList();
    }
}
