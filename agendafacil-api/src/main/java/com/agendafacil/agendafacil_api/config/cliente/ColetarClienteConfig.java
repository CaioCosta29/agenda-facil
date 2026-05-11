package com.agendafacil.agendafacil_api.config.cliente;

import com.agendafacil.agendafacil_api.application.core.usecase.cliente.ColetarClientesUseCase;
import com.agendafacil.agendafacil_api.application.ports.out.cliente.ColetarClientesOutputPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ColetarClienteConfig {

    @Bean
    public ColetarClientesUseCase coletarClientesUseCase(
            ColetarClientesOutputPort coletarClientesOutputPort
    ) {
        return new ColetarClientesUseCase(coletarClientesOutputPort);
    }
}
