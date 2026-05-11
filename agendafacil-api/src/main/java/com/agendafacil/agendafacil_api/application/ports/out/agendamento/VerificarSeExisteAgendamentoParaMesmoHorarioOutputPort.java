package com.agendafacil.agendafacil_api.application.ports.out.agendamento;

import java.time.LocalDateTime;

public interface VerificarSeExisteAgendamentoParaMesmoHorarioOutputPort {

    Boolean verificar(Long idProfissional, LocalDateTime dataHora);
}
