package com.agendafacil.agendafacil_api.application.core.exception;

import java.time.LocalDateTime;

public class AgendamentoConflitanteException extends RuntimeException {

    public AgendamentoConflitanteException(Long idProfissional, LocalDateTime dataHora) {
        super("Já existe um agendamento para o profissional " + idProfissional + " no horário " + dataHora);
    }
}
