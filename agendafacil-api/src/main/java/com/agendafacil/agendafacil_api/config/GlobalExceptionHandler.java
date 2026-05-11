package com.agendafacil.agendafacil_api.config;

import com.agendafacil.agendafacil_api.application.core.exception.AgendamentoConflitanteException;
import com.agendafacil.agendafacil_api.application.core.exception.AgendamentoNaoEncontradoException;
import com.agendafacil.agendafacil_api.application.core.exception.ClienteNaoEncontradoException;
import com.agendafacil.agendafacil_api.application.core.exception.TransicaoStatusInvalidaException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AgendamentoConflitanteException.class)
    public ResponseEntity<String> handleAgendamentoConflitante(AgendamentoConflitanteException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

    @ExceptionHandler(ClienteNaoEncontradoException.class)
    public ResponseEntity<String> handleClienteNaoEncontrado(ClienteNaoEncontradoException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(AgendamentoNaoEncontradoException.class)
    public ResponseEntity<String> handleAgendamentoNaoEncontrado(AgendamentoNaoEncontradoException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(TransicaoStatusInvalidaException.class)
    public ResponseEntity<String> handleTransicaoStatusInvalida(TransicaoStatusInvalidaException ex) {
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(ex.getMessage());
    }

}
