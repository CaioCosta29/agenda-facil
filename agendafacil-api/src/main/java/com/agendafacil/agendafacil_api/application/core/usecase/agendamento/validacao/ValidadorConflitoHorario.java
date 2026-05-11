package com.agendafacil.agendafacil_api.application.core.usecase.agendamento.validacao;

import com.agendafacil.agendafacil_api.application.core.exception.AgendamentoConflitanteException;
import com.agendafacil.agendafacil_api.application.ports.out.agendamento.VerificarSeExisteAgendamentoParaMesmoHorarioOutputPort;

import java.time.LocalDateTime;

public class ValidadorConflitoHorario implements ValidadorAgendamento {

    private final VerificarSeExisteAgendamentoParaMesmoHorarioOutputPort verificarSeExisteAgendamentoParaMesmoHorarioOutputPort;

    public ValidadorConflitoHorario(VerificarSeExisteAgendamentoParaMesmoHorarioOutputPort verificarSeExisteAgendamentoParaMesmoHorarioOutputPort) {
        this.verificarSeExisteAgendamentoParaMesmoHorarioOutputPort = verificarSeExisteAgendamentoParaMesmoHorarioOutputPort;
    }

    @Override
    public void validar(Long idProfissional, LocalDateTime dataHora) {
        boolean conflitoExiste = verificarSeExisteAgendamentoParaMesmoHorarioOutputPort.verificar(idProfissional, dataHora);
        if (conflitoExiste) {
            throw new AgendamentoConflitanteException(idProfissional, dataHora);
        }
    }
}
