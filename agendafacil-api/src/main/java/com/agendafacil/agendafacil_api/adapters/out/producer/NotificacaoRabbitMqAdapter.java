package com.agendafacil.agendafacil_api.adapters.out.producer;

import com.agendafacil.agendafacil_api.adapters.out.producer.mapper.AgendamentoConfirmadoEventMapper;
import com.agendafacil.agendafacil_api.application.core.domain.Agendamento;
import com.agendafacil.agendafacil_api.application.ports.out.agendamento.EnviarNotificacaoOutputPort;
import com.agendafacil.agendafacil_api.config.RabbitMqConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class NotificacaoRabbitMqAdapter implements EnviarNotificacaoOutputPort {

    @Autowired
    private AgendamentoConfirmadoEventMapper agendamentoConfirmadoEventMapper;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public void enviar(Agendamento agendamento) {
        var agendamentoEvent = agendamentoConfirmadoEventMapper.toEvent(agendamento);

        rabbitTemplate.convertAndSend(RabbitMqConfig.EXCHANGE_AGENDAMENTO, "", agendamentoEvent);
    }
}
