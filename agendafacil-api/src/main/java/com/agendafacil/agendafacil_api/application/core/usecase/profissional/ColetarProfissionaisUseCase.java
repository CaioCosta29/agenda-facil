package com.agendafacil.agendafacil_api.application.core.usecase.profissional;

import com.agendafacil.agendafacil_api.application.core.domain.Profissional;
import com.agendafacil.agendafacil_api.application.ports.in.profissional.ColetarProfissionaisInputPort;
import com.agendafacil.agendafacil_api.application.ports.out.profissional.ColetarProfissionaisOutputPort;

import java.util.List;

public class ColetarProfissionaisUseCase implements ColetarProfissionaisInputPort {

    private final ColetarProfissionaisOutputPort coletarProfissionaisOutputPort;

    public ColetarProfissionaisUseCase(ColetarProfissionaisOutputPort coletarProfissionaisOutputPort) {
        this.coletarProfissionaisOutputPort = coletarProfissionaisOutputPort;
    }

    @Override
    public List<Profissional> coletar() {
        return coletarProfissionaisOutputPort.coletar();
    }
}
