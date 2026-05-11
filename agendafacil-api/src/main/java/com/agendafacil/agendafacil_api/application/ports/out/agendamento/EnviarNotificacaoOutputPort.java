package com.agendafacil.agendafacil_api.application.ports.out.agendamento;

import com.agendafacil.agendafacil_api.application.core.domain.Agendamento;

public interface EnviarNotificacaoOutputPort {

    void enviar(Agendamento agendamento);
}
