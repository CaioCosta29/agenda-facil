package com.agendafacil.agendafacil_api.config.agendamento;

import com.agendafacil.agendafacil_api.application.core.usecase.agendamento.ObterAgendamentosUseCase;
import com.agendafacil.agendafacil_api.application.ports.out.agendamento.ObterAgendamentosOutputPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ObterAgendamentosConfig {

    @Bean
    public ObterAgendamentosUseCase obterAgendamentosUseCase(
            ObterAgendamentosOutputPort obterAgendamentosOutputPort
    ) {
        return new ObterAgendamentosUseCase(obterAgendamentosOutputPort);
    }
}
