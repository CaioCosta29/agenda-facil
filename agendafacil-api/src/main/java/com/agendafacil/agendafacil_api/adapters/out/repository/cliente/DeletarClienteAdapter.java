package com.agendafacil.agendafacil_api.adapters.out.repository.cliente;

import com.agendafacil.agendafacil_api.application.ports.out.cliente.DeletarClienteOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeletarClienteAdapter implements DeletarClienteOutputPort {

    private final ClienteRepository clienteRepository;

    @Override
    public void deletar(Long id) {
        clienteRepository.deleteById(id);
    }
}
