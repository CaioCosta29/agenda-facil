package com.agendafacil.agendafacil_api.adapters.out.repository.cliente;

import com.agendafacil.agendafacil_api.adapters.out.repository.cliente.mapper.ClienteEntityMapper;
import com.agendafacil.agendafacil_api.application.core.domain.Cliente;
import com.agendafacil.agendafacil_api.application.ports.out.cliente.CadastrarClienteOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CadastrarClienteAdapter implements CadastrarClienteOutputPort {


    private final ClienteRepository clienteRepository;

    private final ClienteEntityMapper clienteEntityMapper;

    @Override
    public Cliente cadastrar(Cliente cliente) {
        var clienteEntity = clienteEntityMapper.toEntity(cliente);
        var clienteSalvo = clienteRepository.save(clienteEntity);

        return clienteEntityMapper.toDomain(clienteSalvo);
    }
}
