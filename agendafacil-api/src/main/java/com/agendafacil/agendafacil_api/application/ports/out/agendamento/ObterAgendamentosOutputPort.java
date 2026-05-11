package com.agendafacil.agendafacil_api.application.ports.out.agendamento;

import com.agendafacil.agendafacil_api.application.core.domain.Agendamento;

import java.util.List;

public interface ObterAgendamentosOutputPort {

    List<Agendamento> obter();
}
