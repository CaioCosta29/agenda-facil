package com.agendafacil.agendafacil_api.adapters.out.repository.cliente;

import com.agendafacil.agendafacil_api.adapters.out.repository.cliente.entity.ClienteEntity;
import com.agendafacil.agendafacil_api.adapters.out.repository.cliente.mapper.ClienteEntityMapper;
import com.agendafacil.agendafacil_api.application.core.domain.Cliente;
import com.agendafacil.agendafacil_api.application.ports.out.cliente.AtualizarClienteOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AtualizarClienteAdapter implements AtualizarClienteOutputPort {

    private final ClienteRepository clienteRepository;
    private final ClienteEntityMapper clienteEntityMapper;

    @Override
    public Cliente atualizar(Long id, Cliente cliente) {
        var clienteEntity = clienteRepository.findById(id).orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        atualizarCampos(clienteEntity, cliente);
        clienteRepository.save(clienteEntity);

        return clienteEntityMapper.toDomain(clienteEntity);
    }

    private void atualizarCampos(ClienteEntity entity, Cliente cliente) {
        if (cliente.getEmail() != null) entity.setEmail(cliente.getEmail());
        if (cliente.getTelefone() != null) entity.setTelefone(cliente.getTelefone());
    }
}
