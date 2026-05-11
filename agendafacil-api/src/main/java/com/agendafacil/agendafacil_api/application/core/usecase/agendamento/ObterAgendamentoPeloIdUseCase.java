package com.agendafacil.agendafacil_api.application.core.usecase.agendamento;

import com.agendafacil.agendafacil_api.application.core.domain.Agendamento;
import com.agendafacil.agendafacil_api.application.ports.in.agendamento.ObterAgendamentoPeloIdInputPort;
import com.agendafacil.agendafacil_api.application.ports.out.agendamento.ObterAgendamentoPeloIdOutputPort;

public class ObterAgendamentoPeloIdUseCase implements ObterAgendamentoPeloIdInputPort {

    private final ObterAgendamentoPeloIdOutputPort obterAgendamentoPeloIdOutputPort;

    public ObterAgendamentoPeloIdUseCase(ObterAgendamentoPeloIdOutputPort obterAgendamentoPeloIdOutputPort) {
        this.obterAgendamentoPeloIdOutputPort = obterAgendamentoPeloIdOutputPort;
    }

    @Override
    public Agendamento obter(Long id) {
        return obterAgendamentoPeloIdOutputPort.obter(id);
    }
}
