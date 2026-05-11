package com.agendafacil.agendafacil_api.application.ports.in.cliente;

import com.agendafacil.agendafacil_api.application.core.domain.Cliente;

public interface AtualizarClienteInputPort {

    Cliente atualizar(Long id, Cliente cliente);
}
