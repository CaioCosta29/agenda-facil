package com.agendafacil.agendafacil_api.application.ports.out.cliente;

import com.agendafacil.agendafacil_api.application.core.domain.Cliente;

import java.util.List;

public interface ColetarClientesOutputPort {

    List<Cliente> coletar();
}
