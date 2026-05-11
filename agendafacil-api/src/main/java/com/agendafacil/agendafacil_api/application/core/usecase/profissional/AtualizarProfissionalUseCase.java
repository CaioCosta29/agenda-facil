package com.agendafacil.agendafacil_api.application.core.usecase.profissional;

import com.agendafacil.agendafacil_api.application.core.domain.Profissional;
import com.agendafacil.agendafacil_api.application.ports.in.profissional.AtualizarProfissionalInputPort;
import com.agendafacil.agendafacil_api.application.ports.out.profissional.AtualizarProfissionalOutputPort;

public class AtualizarProfissionalUseCase implements AtualizarProfissionalInputPort {

    private final AtualizarProfissionalOutputPort atualizarProfissionalOutputPort;

    public AtualizarProfissionalUseCase(AtualizarProfissionalOutputPort atualizarProfissionalOutputPort) {
        this.atualizarProfissionalOutputPort = atualizarProfissionalOutputPort;
    }

    @Override
    public Profissional atualizar(Long id, Profissional profissional) {
        return atualizarProfissionalOutputPort.atualizar(id, profissional);
    }
}
