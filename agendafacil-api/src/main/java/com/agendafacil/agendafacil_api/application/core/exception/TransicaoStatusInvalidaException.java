package com.agendafacil.agendafacil_api.application.core.exception;

import com.agendafacil.agendafacil_api.application.core.domain.StatusAgendamento;

public class TransicaoStatusInvalidaException extends RuntimeException {

    public TransicaoStatusInvalidaException(StatusAgendamento atual, StatusAgendamento novo) {
        super("Transição de status inválida: " + atual + " → " + novo);
    }
}
