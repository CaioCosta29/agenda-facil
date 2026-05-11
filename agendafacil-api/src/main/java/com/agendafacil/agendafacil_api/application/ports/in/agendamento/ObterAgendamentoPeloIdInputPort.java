package com.agendafacil.agendafacil_api.application.ports.in.agendamento;

import com.agendafacil.agendafacil_api.application.core.domain.Agendamento;

public interface ObterAgendamentoPeloIdInputPort {

    Agendamento obter(Long id);
}
