package com.agendafacil.notificacao.adapters.in.consumer.dto;

import java.time.LocalDateTime;

public record AgendamentoConfirmadoEvent(
        Long agendamentoId,
        String nomeCliente,
        String emailCliente,
        String nomeProfissional,
        String emailProfissional,
        LocalDateTime dataHora
) {
}
