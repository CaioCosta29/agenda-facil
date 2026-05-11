package com.agendafacil.agendafacil_api.application.ports.out.profissional;

import com.agendafacil.agendafacil_api.application.core.domain.Profissional;

import java.util.List;

public interface ColetarProfissionaisOutputPort {

    List<Profissional> coletar();

}
