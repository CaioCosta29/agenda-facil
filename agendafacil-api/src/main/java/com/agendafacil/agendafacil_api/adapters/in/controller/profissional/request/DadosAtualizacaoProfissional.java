package com.agendafacil.agendafacil_api.adapters.in.controller.profissional.request;

import com.agendafacil.agendafacil_api.application.core.domain.Especialidade;
import jakarta.validation.constraints.Email;

public record DadosAtualizacaoProfissional(
        String nome,

        Especialidade especialidade,

        @Email
        String email
) {
}
