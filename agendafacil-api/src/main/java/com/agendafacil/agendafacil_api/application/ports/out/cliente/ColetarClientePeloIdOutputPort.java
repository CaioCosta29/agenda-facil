package com.agendafacil.agendafacil_api.application.ports.out.cliente;

import com.agendafacil.agendafacil_api.application.core.domain.Cliente;

public interface ColetarClientePeloIdOutputPort {

    Cliente coletar(Long id);
}
