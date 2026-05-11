package com.agendafacil.agendafacil_api.application.ports.in.profissional;

import com.agendafacil.agendafacil_api.application.core.domain.Profissional;

public interface CadastrarProfissionalInputPort {

    Profissional cadastrar(Profissional profissional);
}
