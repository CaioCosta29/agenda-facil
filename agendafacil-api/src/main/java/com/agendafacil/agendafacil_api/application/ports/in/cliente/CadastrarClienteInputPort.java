package com.agendafacil.agendafacil_api.application.ports.in.cliente;


import com.agendafacil.agendafacil_api.application.core.domain.Cliente;

public interface CadastrarClienteInputPort {

    Cliente cadastrar(Cliente cliente);
}
