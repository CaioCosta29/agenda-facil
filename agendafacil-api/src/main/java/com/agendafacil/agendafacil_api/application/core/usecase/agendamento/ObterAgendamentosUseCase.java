package com.agendafacil.agendafacil_api.application.core.usecase.agendamento;

import com.agendafacil.agendafacil_api.application.core.domain.Agendamento;
import com.agendafacil.agendafacil_api.application.ports.in.agendamento.ObterAgendamentosInputPort;
import com.agendafacil.agendafacil_api.application.ports.out.agendamento.ObterAgendamentosOutputPort;

import java.util.List;

public class ObterAgendamentosUseCase implements ObterAgendamentosInputPort {

    private final ObterAgendamentosOutputPort obterAgendamentosOutputPort;

    public ObterAgendamentosUseCase(ObterAgendamentosOutputPort obterAgendamentosOutputPort) {
        this.obterAgendamentosOutputPort = obterAgendamentosOutputPort;
    }

    @Override
    public List<Agendamento> obter() {
        return obterAgendamentosOutputPort.obter();
    }
}
