package com.agendafacil.agendafacil_api.config.cliente;

import com.agendafacil.agendafacil_api.application.core.usecase.cliente.AtualizarClienteUseCase;
import com.agendafacil.agendafacil_api.application.ports.out.cliente.AtualizarClienteOutputPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AtualizarClienteConfig {

    @Bean
    public AtualizarClienteUseCase atualizarClienteUseCase (
            AtualizarClienteOutputPort atualizarClienteOutputPort
    ) {
        return new AtualizarClienteUseCase(atualizarClienteOutputPort);
    }
}
