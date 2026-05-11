package com.agendafacil.agendafacil_api.application.core.usecase.profissional;

import com.agendafacil.agendafacil_api.application.core.domain.Profissional;
import com.agendafacil.agendafacil_api.application.ports.in.profissional.CadastrarProfissionalInputPort;
import com.agendafacil.agendafacil_api.application.ports.out.profissional.CadastrarProfissionalOutputPort;

public class CadastrarProfissionalUseCase implements CadastrarProfissionalInputPort {

    private final CadastrarProfissionalOutputPort cadastrarProfissionalOutputPort;

    public CadastrarProfissionalUseCase(CadastrarProfissionalOutputPort cadastrarProfissionalOutputPort) {
        this.cadastrarProfissionalOutputPort = cadastrarProfissionalOutputPort;
    }

    @Override
    public Profissional cadastrar(Profissional profissional) {
        return cadastrarProfissionalOutputPort.cadastrar(profissional);
    }
}
