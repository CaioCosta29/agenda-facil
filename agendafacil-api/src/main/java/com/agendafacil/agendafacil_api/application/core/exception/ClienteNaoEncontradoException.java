package com.agendafacil.agendafacil_api.application.core.exception;

public class ClienteNaoEncontradoException extends RuntimeException {

    public ClienteNaoEncontradoException(Long id) {
        super("Cliente não encontrado com id: " + id);
    }
}
