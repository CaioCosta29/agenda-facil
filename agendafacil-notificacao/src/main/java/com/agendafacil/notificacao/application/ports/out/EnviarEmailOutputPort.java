package com.agendafacil.notificacao.application.ports.out;

public interface EnviarEmailOutputPort {

    void enviar(String destinatario, String assunto, String corpo);
}
