package com.agendafacil.agendafacil_api.application.ports.in.profissional;

import com.agendafacil.agendafacil_api.application.core.domain.Profissional;

public interface AtualizarProfissionalInputPort {

    Profissional atualizar(Long id, Profissional profissional);
}
