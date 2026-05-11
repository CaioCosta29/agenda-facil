package com.agendafacil.agendafacil_api.adapters.in.controller.agendamento.response;

import com.agendafacil.agendafacil_api.application.core.domain.StatusAgendamento;

import java.time.LocalDateTime;

public record DadosDetalhamentoAgendamento(
        Long id,
        Long idCliente,
        String nomeCliente,
        Long idProfissional,
        String nomeProfissional,
        LocalDateTime dataHora,
        StatusAgendamento status
) {
}
