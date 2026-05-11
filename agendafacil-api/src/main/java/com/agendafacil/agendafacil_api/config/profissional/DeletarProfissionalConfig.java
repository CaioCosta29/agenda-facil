package com.agendafacil.agendafacil_api.config.profissional;

import com.agendafacil.agendafacil_api.application.core.usecase.profissional.DeletarProfissionalUseCase;
import com.agendafacil.agendafacil_api.application.ports.out.profissional.ColetarProfissionalPeloIdOutputPort;
import com.agendafacil.agendafacil_api.application.ports.out.profissional.DeletarProfissionalOutputPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DeletarProfissionalConfig {

    @Bean
    public DeletarProfissionalUseCase deletarProfissionalUseCase(
            DeletarProfissionalOutputPort deletarProfissionalOutputPort,
            ColetarProfissionalPeloIdOutputPort coletarProfissionalPeloIdOutputPort
    ) {
        return new DeletarProfissionalUseCase(deletarProfissionalOutputPort, coletarProfissionalPeloIdOutputPort);
    }
}
