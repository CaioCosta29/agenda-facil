package com.agendafacil.agendafacil_api.application.ports.out.agendamento;

import com.agendafacil.agendafacil_api.application.core.domain.Agendamento;

import java.time.LocalDate;
import java.util.List;

public interface ObterAgendaProfissionalPelaDataOutputPort {

    List<Agendamento> obter(Long id, LocalDate data);

}
