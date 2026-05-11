package com.agendafacil.agendafacil_api.adapters.in.controller.agendamento.request;

import com.agendafacil.agendafacil_api.application.core.domain.StatusAgendamento;

public record DadosAtualizacaoAgendamento(
        StatusAgendamento status
) {
}
