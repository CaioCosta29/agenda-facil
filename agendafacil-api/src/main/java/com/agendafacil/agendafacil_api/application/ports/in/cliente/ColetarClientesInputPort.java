package com.agendafacil.agendafacil_api.application.ports.in.cliente;

import com.agendafacil.agendafacil_api.application.core.domain.Cliente;

import java.util.List;

public interface ColetarClientesInputPort {
    List<Cliente> coletar();
}
