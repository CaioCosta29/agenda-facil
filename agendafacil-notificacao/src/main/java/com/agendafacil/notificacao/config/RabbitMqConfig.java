package com.agendafacil.notificacao.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

    public static final String EXCHANGE_AGENDAMENTO = "agendamento.ex";
    public static final String CLIENTE_QUEUE = "agendamento.cliente.queue";
    public static final String PROFISSIONAL_QUEUE = "agendamento.profissional.queue";
    public static final String PROFISSIONAL_DLQ = "agendamento.profissional.dlq";
    public static final String CLIENTE_DLQ = "agendamento.cliente.dlq";
    public static final String EXCHANGE_AGENDAMENTO_DLX = "agendamento.dlx";

    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(EXCHANGE_AGENDAMENTO);
    }

    @Bean
    public Queue clienteQueue() {
        return QueueBuilder
                .durable(CLIENTE_QUEUE)
                .deadLetterExchange(EXCHANGE_AGENDAMENTO_DLX)
                .deadLetterRoutingKey(CLIENTE_DLQ)
                .build();
    }

    @Bean
    public Queue profissionalQueue() {
        return QueueBuilder
                .durable(PROFISSIONAL_QUEUE)
                .deadLetterExchange(EXCHANGE_AGENDAMENTO_DLX)
                .deadLetterRoutingKey(PROFISSIONAL_DLQ)
                .build();
    }

    @Bean
    public Binding bindingCliente(Queue clienteQueue, FanoutExchange exchange) {
        return BindingBuilder.bind(clienteQueue).to(exchange);
    }

    @Bean
    public Binding bindingProfissional(Queue profissionalQueue, FanoutExchange exchange) {
        return BindingBuilder.bind(profissionalQueue).to(exchange);
    }

    @Bean
    public JacksonJsonMessageConverter messageConverter() {
        return new JacksonJsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory,
                                         JacksonJsonMessageConverter messageConverter) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter);
        return rabbitTemplate;
    }

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(EXCHANGE_AGENDAMENTO_DLX);
    }

    @Bean
    public Queue clienteDlq() {
        return new Queue(CLIENTE_DLQ, true);
    }

    @Bean
    public Queue profissionalDlq() {
        return new Queue(PROFISSIONAL_DLQ, true);
    }

    @Bean
    public Binding bindingClienteDlq(Queue clienteDlq, DirectExchange directExchange) {
        return BindingBuilder.bind(clienteDlq).to(directExchange).with(CLIENTE_DLQ);
    }

    @Bean
    public Binding bindingProfissinalDlq(Queue profissionalDlq, DirectExchange directExchange) {
        return BindingBuilder.bind(profissionalDlq).to(directExchange).with(PROFISSIONAL_DLQ);
    }
}
