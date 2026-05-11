package com.agendafacil.agendafacil_api.application.ports.out.agendamento;

import com.agendafacil.agendafacil_api.application.core.domain.Agendamento;

public interface AtualizarStatusAgendamentoOutputPort {

    Agendamento atualizar(Agendamento agendamento);
}
