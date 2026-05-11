package com.agendafacil.agendafacil_api.adapters.in.controller.agendamento.request;


import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DadosCriacaoAgendamento(
        @NotNull
        Long idCliente,

        @NotNull
        Long idProfissional,

        @NotNull
        @Future
        LocalDateTime dataHora
) {
}
