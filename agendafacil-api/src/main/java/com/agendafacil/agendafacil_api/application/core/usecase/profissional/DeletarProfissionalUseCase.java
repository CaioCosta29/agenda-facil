package com.agendafacil.agendafacil_api.application.core.usecase.profissional;

import com.agendafacil.agendafacil_api.application.ports.in.profissional.DeletarProfissionalInputPort;
import com.agendafacil.agendafacil_api.application.ports.out.profissional.ColetarProfissionalPeloIdOutputPort;
import com.agendafacil.agendafacil_api.application.ports.out.profissional.DeletarProfissionalOutputPort;

public class DeletarProfissionalUseCase implements DeletarProfissionalInputPort {

    private final DeletarProfissionalOutputPort deletarProfissionalOutputPort;
    private final ColetarProfissionalPeloIdOutputPort coletarProfissionalPeloIdOutputPort;

    public DeletarProfissionalUseCase(DeletarProfissionalOutputPort deletarProfissionalOutputPort, ColetarProfissionalPeloIdOutputPort coletarProfissionalPeloIdOutputPort) {
        this.deletarProfissionalOutputPort = deletarProfissionalOutputPort;
        this.coletarProfissionalPeloIdOutputPort = coletarProfissionalPeloIdOutputPort;
    }

    @Override
    public void deletar(Long id) {
        coletarProfissionalPeloIdOutputPort.coletar(id);

        deletarProfissionalOutputPort.deletar(id);
    }
}
