package com.agendafacil.agendafacil_api.application.ports.out.profissional;

import com.agendafacil.agendafacil_api.application.core.domain.Profissional;

public interface CadastrarProfissionalOutputPort {

    Profissional cadastrar(Profissional profissional);
}
