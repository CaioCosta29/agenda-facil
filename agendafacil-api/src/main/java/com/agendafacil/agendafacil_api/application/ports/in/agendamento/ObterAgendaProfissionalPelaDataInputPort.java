package com.agendafacil.agendafacil_api.application.ports.in.agendamento;

import com.agendafacil.agendafacil_api.application.core.domain.Agendamento;

import java.time.LocalDate;
import java.util.List;

public interface ObterAgendaProfissionalPelaDataInputPort {

    List<Agendamento> obter(Long id, LocalDate data);
}
