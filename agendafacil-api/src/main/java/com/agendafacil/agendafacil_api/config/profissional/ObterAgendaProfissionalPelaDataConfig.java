package com.agendafacil.agendafacil_api.config.profissional;

import com.agendafacil.agendafacil_api.application.core.usecase.profissional.ObterAgendaProfissionalPelaDataUseCase;
import com.agendafacil.agendafacil_api.application.ports.out.agendamento.ObterAgendaProfissionalPelaDataOutputPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ObterAgendaProfissionalPelaDataConfig {

    @Bean
    public ObterAgendaProfissionalPelaDataUseCase obterAgendaProfissionalPelaDataUseCase(
            ObterAgendaProfissionalPelaDataOutputPort obterAgendaProfissionalPelaDataOutputPort
    ) {
        return new ObterAgendaProfissionalPelaDataUseCase(obterAgendaProfissionalPelaDataOutputPort);
    }
}
