package com.agendafacil.agendafacil_api.config.agendamento;

import com.agendafacil.agendafacil_api.application.core.usecase.agendamento.FazerAgendamentoUseCase;
import com.agendafacil.agendafacil_api.application.core.usecase.agendamento.validacao.ValidadorAgendamento;
import com.agendafacil.agendafacil_api.application.core.usecase.agendamento.validacao.ValidadorConflitoHorario;
import com.agendafacil.agendafacil_api.application.ports.out.agendamento.FazerAgendamentoOutputPort;
import com.agendafacil.agendafacil_api.application.ports.out.agendamento.VerificarSeExisteAgendamentoParaMesmoHorarioOutputPort;
import com.agendafacil.agendafacil_api.application.ports.out.cliente.ColetarClientePeloIdOutputPort;
import com.agendafacil.agendafacil_api.application.ports.out.profissional.ColetarProfissionalPeloIdOutputPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class FazerAgendamentoConfig {

    @Bean
    public ValidadorConflitoHorario validadorConflitoHorario(
            VerificarSeExisteAgendamentoParaMesmoHorarioOutputPort outputPort) {
        return new ValidadorConflitoHorario(outputPort);
    }

    @Bean
    public FazerAgendamentoUseCase fazerAgendamentoUseCase(
            ColetarClientePeloIdOutputPort coletarClientePeloIdOutputPort,
            ColetarProfissionalPeloIdOutputPort coletarProfissionalPeloIdOutputPort,
            FazerAgendamentoOutputPort fazerAgendamentoOutputPort,
            List<ValidadorAgendamento> validadores
    ) {
        return new FazerAgendamentoUseCase(coletarClientePeloIdOutputPort, coletarProfissionalPeloIdOutputPort, fazerAgendamentoOutputPort, validadores);
    }

}
