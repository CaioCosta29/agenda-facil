package com.agendafacil.agendafacil_api.adapters.in.controller.profissional.response;

import com.agendafacil.agendafacil_api.application.core.domain.Especialidade;

public record DadosDetalhamentoProfissional(
        Long id,
        String nome,
        Especialidade especialidade,
        String email
) {
}
