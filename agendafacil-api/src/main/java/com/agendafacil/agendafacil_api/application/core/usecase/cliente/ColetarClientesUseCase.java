package com.agendafacil.agendafacil_api.application.core.usecase.cliente;

import com.agendafacil.agendafacil_api.application.core.domain.Cliente;
import com.agendafacil.agendafacil_api.application.ports.in.cliente.ColetarClientesInputPort;
import com.agendafacil.agendafacil_api.application.ports.out.cliente.ColetarClientesOutputPort;

import java.util.List;

public class ColetarClientesUseCase implements ColetarClientesInputPort {

    private final ColetarClientesOutputPort coletarClientesOutputPort;

    public ColetarClientesUseCase(ColetarClientesOutputPort coletarClientesOutputPort) {
        this.coletarClientesOutputPort = coletarClientesOutputPort;
    }

    @Override
    public List<Cliente> coletar() {
        var clientes = coletarClientesOutputPort.coletar();

        return clientes;
    }
}
