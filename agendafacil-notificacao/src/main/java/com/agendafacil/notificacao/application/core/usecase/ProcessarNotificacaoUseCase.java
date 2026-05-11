package com.agendafacil.notificacao.application.core.usecase;

import com.agendafacil.notificacao.adapters.in.consumer.dto.AgendamentoConfirmadoEvent;
import com.agendafacil.notificacao.application.ports.in.ProcessarNotificacaoInputPort;
import com.agendafacil.notificacao.application.ports.out.EnviarEmailOutputPort;

public class ProcessarNotificacaoUseCase implements ProcessarNotificacaoInputPort {

    private final EnviarEmailOutputPort enviarEmailOutputPort;

    public ProcessarNotificacaoUseCase(EnviarEmailOutputPort enviarEmailOutputPort) {
        this.enviarEmailOutputPort = enviarEmailOutputPort;
    }

    @Override
    public void processarCliente(AgendamentoConfirmadoEvent event) {
        enviarEmailOutputPort.enviar(
                event.emailCliente(),
                "Agendamento confirmado",
                "Olá " + event.nomeCliente() + ", seu agendamento foi confirmado para " + event.dataHora()
        );
    }

    @Override
    public void processarProfissional(AgendamentoConfirmadoEvent event) {
        enviarEmailOutputPort.enviar(
                event.emailProfissional(),
                "Novo agendamento",
                "Ola mãe, como você esta? Tenha um bom dia. Te amo!"
        );
    }
}
