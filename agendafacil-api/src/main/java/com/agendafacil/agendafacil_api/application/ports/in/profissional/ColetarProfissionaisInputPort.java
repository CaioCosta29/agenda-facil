package com.agendafacil.agendafacil_api.application.ports.in.profissional;

import com.agendafacil.agendafacil_api.application.core.domain.Profissional;

import java.util.List;

public interface ColetarProfissionaisInputPort {

    List<Profissional> coletar();
}
