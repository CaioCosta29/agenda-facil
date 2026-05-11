package com.agendafacil.agendafacil_api.application.core.usecase.agendamento;

import com.agendafacil.agendafacil_api.application.core.domain.Agendamento;
import com.agendafacil.agendafacil_api.application.core.domain.Cliente;
import com.agendafacil.agendafacil_api.application.core.domain.Profissional;
import com.agendafacil.agendafacil_api.application.core.domain.StatusAgendamento;
import com.agendafacil.agendafacil_api.application.core.usecase.agendamento.validacao.ValidadorAgendamento;
import com.agendafacil.agendafacil_api.application.ports.in.agendamento.FazerAgendamentoInputPort;
import com.agendafacil.agendafacil_api.application.ports.out.agendamento.FazerAgendamentoOutputPort;
import com.agendafacil.agendafacil_api.application.ports.out.cliente.ColetarClientePeloIdOutputPort;
import com.agendafacil.agendafacil_api.application.ports.out.profissional.ColetarProfissionalPeloIdOutputPort;

import java.time.LocalDateTime;
import java.util.List;

public class FazerAgendamentoUseCase implements FazerAgendamentoInputPort {

    private final ColetarClientePeloIdOutputPort coletarClientePeloIdOutputPort;
    private final ColetarProfissionalPeloIdOutputPort coletarProfissionalPeloIdOutputPort;
    private final FazerAgendamentoOutputPort fazerAgendamentoOutputPort;
    private final List<ValidadorAgendamento> validadores;

    public FazerAgendamentoUseCase(ColetarClientePeloIdOutputPort coletarClientePeloIdOutputPort,
                                   ColetarProfissionalPeloIdOutputPort coletarProfissionalPeloIdOutputPort,
                                   FazerAgendamentoOutputPort fazerAgendamentoOutputPort,
                                   List<ValidadorAgendamento> validadores) {
        this.coletarClientePeloIdOutputPort = coletarClientePeloIdOutputPort;
        this.coletarProfissionalPeloIdOutputPort = coletarProfissionalPeloIdOutputPort;
        this.fazerAgendamentoOutputPort = fazerAgendamentoOutputPort;
        this.validadores = validadores;
    }

    @Override
    public Agendamento agendar(Long idCliente, Long idProfissional, LocalDateTime dataHora) {
        validadores.forEach(validador -> validador.validar(idProfissional, dataHora));

        var cliente = coletarClientePeloIdOutputPort.coletar(idCliente);
        var profissional = coletarProfissionalPeloIdOutputPort.coletar(idProfissional);

        var agendamento = criarAgendamento(cliente, profissional, dataHora);

        return fazerAgendamentoOutputPort.agendar(agendamento);
    }

    private Agendamento criarAgendamento(Cliente cliente, Profissional profissional, LocalDateTime dataHora) {
        var agendamento = new Agendamento();
        agendamento.setCliente(cliente);
        agendamento.setProfissional(profissional);
        agendamento.setDataHora(dataHora);
        agendamento.setStatusAgendamento(StatusAgendamento.PENDENTE);

        return agendamento;
    }
}
