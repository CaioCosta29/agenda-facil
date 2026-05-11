package com.agendafacil.agendafacil_api.config.cliente;

import com.agendafacil.agendafacil_api.application.core.usecase.cliente.DeletarClienteUseCase;
import com.agendafacil.agendafacil_api.application.ports.out.cliente.ColetarClientePeloIdOutputPort;
import com.agendafacil.agendafacil_api.application.ports.out.cliente.DeletarClienteOutputPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DeletarClienteConfig {

    @Bean
    public DeletarClienteUseCase deletarClienteUseCase (
            ColetarClientePeloIdOutputPort coletarClientePeloIdOutputPort,
            DeletarClienteOutputPort deletarClienteOutputPort
    ) {
        return new DeletarClienteUseCase(coletarClientePeloIdOutputPort, deletarClienteOutputPort);
    }
}
