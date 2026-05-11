package com.agendafacil.agendafacil_api.config.agendamento;

import com.agendafacil.agendafacil_api.application.core.usecase.agendamento.AtualizarStatusAgendamentoUseCase;
import com.agendafacil.agendafacil_api.application.ports.out.agendamento.AtualizarStatusAgendamentoOutputPort;
import com.agendafacil.agendafacil_api.application.ports.out.agendamento.EnviarNotificacaoOutputPort;
import com.agendafacil.agendafacil_api.application.ports.out.agendamento.ObterAgendamentoPeloIdOutputPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AtualizarStatusAgendamentoConfig {

    @Bean
    public AtualizarStatusAgendamentoUseCase atualizarStatusAgendamentoUseCase(
            AtualizarStatusAgendamentoOutputPort atualizarStatusAgendamentoOutputPort,
            ObterAgendamentoPeloIdOutputPort obterAgendamentoPeloIdOutputPort,
            EnviarNotificacaoOutputPort enviarNotificacaoOutputPort
    ) {
        return new AtualizarStatusAgendamentoUseCase(atualizarStatusAgendamentoOutputPort, obterAgendamentoPeloIdOutputPort, enviarNotificacaoOutputPort);
    }
}
