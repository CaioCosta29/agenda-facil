package com.agendafacil.agendafacil_api.application.core.usecase.agendamento.validacao;

import java.time.LocalDateTime;

public interface ValidadorAgendamento {

    void validar(Long idProfissional, LocalDateTime dataHora);
}
