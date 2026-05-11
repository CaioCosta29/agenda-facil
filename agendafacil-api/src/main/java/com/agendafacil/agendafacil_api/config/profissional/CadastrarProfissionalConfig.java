package com.agendafacil.agendafacil_api.config.profissional;

import com.agendafacil.agendafacil_api.application.core.usecase.profissional.CadastrarProfissionalUseCase;
import com.agendafacil.agendafacil_api.application.ports.out.profissional.CadastrarProfissionalOutputPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CadastrarProfissionalConfig {

    @Bean
    public CadastrarProfissionalUseCase cadastrarProfissionalUseCase (
            CadastrarProfissionalOutputPort cadastrarProfissionalOutputPort
    ) {
        return new CadastrarProfissionalUseCase(cadastrarProfissionalOutputPort);
    }
}
