package com.agendafacil.agendafacil_api.application.core.usecase.profissional;

import com.agendafacil.agendafacil_api.application.core.domain.Agendamento;
import com.agendafacil.agendafacil_api.application.ports.in.agendamento.ObterAgendaProfissionalPelaDataInputPort;
import com.agendafacil.agendafacil_api.application.ports.out.agendamento.ObterAgendaProfissionalPelaDataOutputPort;

import java.time.LocalDate;
import java.util.List;

public class ObterAgendaProfissionalPelaDataUseCase implements ObterAgendaProfissionalPelaDataInputPort {

    private final ObterAgendaProfissionalPelaDataOutputPort obterAgendaProfissionalPelaDataOutputPort;

    public ObterAgendaProfissionalPelaDataUseCase(ObterAgendaProfissionalPelaDataOutputPort obterAgendaProfissionalPelaDataOutputPort) {
        this.obterAgendaProfissionalPelaDataOutputPort = obterAgendaProfissionalPelaDataOutputPort;
    }

    @Override
    public List<Agendamento> obter(Long id, LocalDate data) {
        return obterAgendaProfissionalPelaDataOutputPort.obter(id, data);
    }
}
