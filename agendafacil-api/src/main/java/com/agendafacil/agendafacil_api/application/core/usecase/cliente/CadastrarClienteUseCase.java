package com.agendafacil.agendafacil_api.application.core.usecase.cliente;

import com.agendafacil.agendafacil_api.application.core.domain.Cliente;
import com.agendafacil.agendafacil_api.application.ports.in.cliente.CadastrarClienteInputPort;
import com.agendafacil.agendafacil_api.application.ports.out.cliente.CadastrarClienteOutputPort;

public class CadastrarClienteUseCase implements CadastrarClienteInputPort {

    private final CadastrarClienteOutputPort cadastrarClienteOutputPort;

    public CadastrarClienteUseCase(CadastrarClienteOutputPort cadastrarClienteOutputPort) {
        this.cadastrarClienteOutputPort = cadastrarClienteOutputPort;
    }

    @Override
    public Cliente cadastrar(Cliente cliente) {
        return cadastrarClienteOutputPort.cadastrar(cliente);
    }
}
