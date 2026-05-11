package com.agendafacil.agendafacil_api.config.agendamento;

import com.agendafacil.agendafacil_api.application.core.usecase.agendamento.ObterAgendamentoPeloIdUseCase;
import com.agendafacil.agendafacil_api.application.ports.out.agendamento.ObterAgendamentoPeloIdOutputPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ObterAgendamentoPeloIdConfig {

    @Bean
    public ObterAgendamentoPeloIdUseCase obterAgendamentoPeloIdUseCase(ObterAgendamentoPeloIdOutputPort obterAgendamentoPeloIdOutputPort) {
        return new ObterAgendamentoPeloIdUseCase(obterAgendamentoPeloIdOutputPort);
    }
}
