package com.agendafacil.agendafacil_api.adapters.out.repository.cliente;

import com.agendafacil.agendafacil_api.adapters.out.repository.cliente.mapper.ClienteEntityMapper;
import com.agendafacil.agendafacil_api.application.core.domain.Cliente;
import com.agendafacil.agendafacil_api.application.ports.out.cliente.ColetarClientesOutputPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ColetarClienteAdapter implements ColetarClientesOutputPort {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ClienteEntityMapper clienteEntityMapper;

    @Override
    public List<Cliente> coletar() {
        var clientesEntity = clienteRepository.findAll();

        return clientesEntity.stream().map(clienteEntity -> clienteEntityMapper.toDomain(clienteEntity)).toList();
    }
}
