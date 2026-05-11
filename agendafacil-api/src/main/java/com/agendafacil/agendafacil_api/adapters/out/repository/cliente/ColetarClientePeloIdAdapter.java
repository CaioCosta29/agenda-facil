package com.agendafacil.agendafacil_api.adapters.out.repository.cliente;

import com.agendafacil.agendafacil_api.adapters.out.repository.cliente.mapper.ClienteEntityMapper;
import com.agendafacil.agendafacil_api.application.core.domain.Cliente;
import com.agendafacil.agendafacil_api.application.core.exception.ClienteNaoEncontradoException;
import com.agendafacil.agendafacil_api.application.ports.out.cliente.ColetarClientePeloIdOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ColetarClientePeloIdAdapter implements ColetarClientePeloIdOutputPort {

    private final ClienteRepository clienteRepository;
    private final ClienteEntityMapper clienteEntityMapper;

    @Override
    public Cliente coletar(Long id) {
        var clienteEntity = clienteRepository.findById(id).orElseThrow(() -> new ClienteNaoEncontradoException(id));

        return clienteEntityMapper.toDomain(clienteEntity);
    }
}
