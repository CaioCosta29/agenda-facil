package com.agendafacil.notificacao.config;

import com.agendafacil.notificacao.application.core.usecase.ProcessarNotificacaoUseCase;
import com.agendafacil.notificacao.application.ports.in.ProcessarNotificacaoInputPort;
import com.agendafacil.notificacao.application.ports.out.EnviarEmailOutputPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProcessarNotificacaoConfig {

    @Bean
    public ProcessarNotificacaoInputPort processarNotificacaoInputPort(EnviarEmailOutputPort enviarEmailOutputPort) {
        return new ProcessarNotificacaoUseCase(enviarEmailOutputPort);
    }
}
