package com.agendafacil.notificacao.adapters.in.consumer;

import com.agendafacil.notificacao.adapters.in.consumer.dto.AgendamentoConfirmadoEvent;
import com.agendafacil.notificacao.application.ports.in.ProcessarNotificacaoInputPort;
import com.agendafacil.notificacao.config.RabbitMqConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class AgendamentoConsumer {

    private final ProcessarNotificacaoInputPort processarNotificacaoInputPort;

    public AgendamentoConsumer(ProcessarNotificacaoInputPort processarNotificacaoInputPort) {
        this.processarNotificacaoInputPort = processarNotificacaoInputPort;
    }

    @RabbitListener(queues = RabbitMqConfig.CLIENTE_QUEUE)
    public void notificarCliente(@Payload AgendamentoConfirmadoEvent agendamentoConfirmadoEvent) {
        processarNotificacaoInputPort.processarCliente(agendamentoConfirmadoEvent);
    }

    @RabbitListener(queues = RabbitMqConfig.PROFISSIONAL_QUEUE)
    public void notificarProfissional(@Payload AgendamentoConfirmadoEvent agendamentoConfirmadoEvent) {
        processarNotificacaoInputPort.processarProfissional(agendamentoConfirmadoEvent);
    }
}
