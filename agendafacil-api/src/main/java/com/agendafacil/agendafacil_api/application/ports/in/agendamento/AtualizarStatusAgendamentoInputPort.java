package com.agendafacil.agendafacil_api.application.ports.in.agendamento;

import com.agendafacil.agendafacil_api.application.core.domain.Agendamento;
import com.agendafacil.agendafacil_api.application.core.domain.StatusAgendamento;

public interface AtualizarStatusAgendamentoInputPort {

    Agendamento atualizar(Long id, StatusAgendamento statusAgendamento);
}
