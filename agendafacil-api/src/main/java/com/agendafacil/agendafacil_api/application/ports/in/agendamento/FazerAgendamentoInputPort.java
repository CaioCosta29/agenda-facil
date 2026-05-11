package com.agendafacil.agendafacil_api.application.ports.in.agendamento;

import com.agendafacil.agendafacil_api.application.core.domain.Agendamento;

import java.time.LocalDateTime;

public interface FazerAgendamentoInputPort {

    Agendamento agendar(Long idCliente, Long idProfissional, LocalDateTime dataHora);
}
