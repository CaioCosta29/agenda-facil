package com.agendafacil.agendafacil_api.application.core.usecase.cliente;

import com.agendafacil.agendafacil_api.application.ports.in.cliente.DeletarClienteInputPort;
import com.agendafacil.agendafacil_api.application.ports.out.cliente.ColetarClientePeloIdOutputPort;
import com.agendafacil.agendafacil_api.application.ports.out.cliente.DeletarClienteOutputPort;

public class DeletarClienteUseCase implements DeletarClienteInputPort {

    private final ColetarClientePeloIdOutputPort coletarClientePeloIdOutputPort;
    private final DeletarClienteOutputPort deletarClienteOutputPort;

    public DeletarClienteUseCase(ColetarClientePeloIdOutputPort coletarClientePeloIdOutputPort, DeletarClienteOutputPort deletarClienteOutputPort) {
        this.coletarClientePeloIdOutputPort = coletarClientePeloIdOutputPort;
        this.deletarClienteOutputPort = deletarClienteOutputPort;
    }

    @Override
    public void deletar(Long id) {
        coletarClientePeloIdOutputPort.coletar(id);
        deletarClienteOutputPort.deletar(id);
    }
}
