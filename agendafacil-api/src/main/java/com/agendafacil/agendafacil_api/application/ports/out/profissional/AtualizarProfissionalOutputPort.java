package com.agendafacil.agendafacil_api.application.ports.out.profissional;

import com.agendafacil.agendafacil_api.application.core.domain.Profissional;

public interface AtualizarProfissionalOutputPort {

    Profissional atualizar(Long id, Profissional profissional);
}
