package com.agendafacil.notificacao.application.ports.in;

import com.agendafacil.notificacao.adapters.in.consumer.dto.AgendamentoConfirmadoEvent;

public interface ProcessarNotificacaoInputPort {

    void processarCliente(AgendamentoConfirmadoEvent event);
    void processarProfissional(AgendamentoConfirmadoEvent event);
}
