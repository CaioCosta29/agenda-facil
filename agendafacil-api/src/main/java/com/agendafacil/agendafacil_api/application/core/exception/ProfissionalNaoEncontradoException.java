package com.agendafacil.agendafacil_api.application.core.exception;

public class ProfissionalNaoEncontradoException extends RuntimeException {

    public ProfissionalNaoEncontradoException(Long id) {
        super("Profissional não encontrado com id: " + id);
    }
}
