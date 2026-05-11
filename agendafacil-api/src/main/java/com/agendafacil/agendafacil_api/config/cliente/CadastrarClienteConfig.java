package com.agendafacil.agendafacil_api.config.cliente;

import com.agendafacil.agendafacil_api.application.core.usecase.cliente.CadastrarClienteUseCase;
import com.agendafacil.agendafacil_api.application.ports.out.cliente.CadastrarClienteOutputPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CadastrarClienteConfig {

    @Bean
    public CadastrarClienteUseCase cadastrarClienteUseCase(
            CadastrarClienteOutputPort cadastrarClienteOutputPort
    ) {
        return new CadastrarClienteUseCase(cadastrarClienteOutputPort);
    }
}




