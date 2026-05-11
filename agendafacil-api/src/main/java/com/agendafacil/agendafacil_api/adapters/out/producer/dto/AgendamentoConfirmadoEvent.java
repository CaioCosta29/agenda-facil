package com.agendafacil.agendafacil_api.adapters.out.producer.dto;

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
