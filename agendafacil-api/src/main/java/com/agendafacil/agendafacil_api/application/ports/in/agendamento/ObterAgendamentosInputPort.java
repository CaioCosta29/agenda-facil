package com.agendafacil.agendafacil_api.application.ports.in.agendamento;

import com.agendafacil.agendafacil_api.application.core.domain.Agendamento;

import java.util.List;

public interface ObterAgendamentosInputPort {

    List<Agendamento> obter();
}
