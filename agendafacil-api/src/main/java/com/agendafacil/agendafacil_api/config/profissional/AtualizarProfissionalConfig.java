package com.agendafacil.agendafacil_api.config.profissional;

import com.agendafacil.agendafacil_api.application.core.usecase.profissional.AtualizarProfissionalUseCase;
import com.agendafacil.agendafacil_api.application.ports.out.profissional.AtualizarProfissionalOutputPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AtualizarProfissionalConfig {

    @Bean
    public AtualizarProfissionalUseCase atualizarProfissionalUseCase(
            AtualizarProfissionalOutputPort atualizarProfissionalOutputPort
    ) {
        return new AtualizarProfissionalUseCase(atualizarProfissionalOutputPort);
    }
}
