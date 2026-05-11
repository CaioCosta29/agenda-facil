package com.agendafacil.agendafacil_api.application.core.usecase.cliente;

import com.agendafacil.agendafacil_api.application.core.domain.Cliente;
import com.agendafacil.agendafacil_api.application.ports.in.cliente.AtualizarClienteInputPort;
import com.agendafacil.agendafacil_api.application.ports.out.cliente.AtualizarClienteOutputPort;

public class AtualizarClienteUseCase implements AtualizarClienteInputPort {

    private final AtualizarClienteOutputPort atualizarClienteOutputPort;

    public AtualizarClienteUseCase(AtualizarClienteOutputPort atualizarClienteOutputPort) {
        this.atualizarClienteOutputPort = atualizarClienteOutputPort;
    }

    @Override
    public Cliente atualizar(Long id, Cliente cliente) {
        return atualizarClienteOutputPort.atualizar(id, cliente);
    }
}
