package com.agendafacil.agendafacil_api.application.core.exception;

public class AgendamentoNaoEncontradoException extends RuntimeException {

    public AgendamentoNaoEncontradoException(Long id) {
        super("Agendamento não encontrado com id: " + id);
    }
}