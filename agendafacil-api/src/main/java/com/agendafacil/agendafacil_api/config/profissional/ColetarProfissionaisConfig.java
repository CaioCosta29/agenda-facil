package com.agendafacil.agendafacil_api.config.profissional;

import com.agendafacil.agendafacil_api.application.core.usecase.profissional.ColetarProfissionaisUseCase;
import com.agendafacil.agendafacil_api.application.ports.out.profissional.ColetarProfissionaisOutputPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ColetarProfissionaisConfig {

    @Bean
    public ColetarProfissionaisUseCase coletarProfissionaisUseCase (
            ColetarProfissionaisOutputPort coletarProfissionaisOutputPort
    ) {
        return new ColetarProfissionaisUseCase(coletarProfissionaisOutputPort);
    }
}
