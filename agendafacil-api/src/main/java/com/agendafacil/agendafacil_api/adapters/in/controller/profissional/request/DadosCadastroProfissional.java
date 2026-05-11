package com.agendafacil.agendafacil_api.adapters.in.controller.profissional.request;

import com.agendafacil.agendafacil_api.application.core.domain.Especialidade;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroProfissional(
        @NotBlank
        String nome,

        @NotNull
        Especialidade especialidade,

        @NotBlank
        @Email
        String email
) {
}
