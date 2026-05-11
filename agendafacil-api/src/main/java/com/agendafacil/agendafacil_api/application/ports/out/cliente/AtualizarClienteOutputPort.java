package com.agendafacil.agendafacil_api.application.ports.out.cliente;

import com.agendafacil.agendafacil_api.application.core.domain.Cliente;

public interface AtualizarClienteOutputPort {

    Cliente atualizar(Long id, Cliente cliente);
}
