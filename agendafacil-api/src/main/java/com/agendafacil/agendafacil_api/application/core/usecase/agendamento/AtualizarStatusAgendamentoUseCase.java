package com.agendafacil.agendafacil_api.application.core.usecase.agendamento;

import com.agendafacil.agendafacil_api.application.core.domain.Agendamento;
import com.agendafacil.agendafacil_api.application.core.domain.StatusAgendamento;
import com.agendafacil.agendafacil_api.application.core.exception.TransicaoStatusInvalidaException;
import com.agendafacil.agendafacil_api.application.ports.in.agendamento.AtualizarStatusAgendamentoInputPort;
import com.agendafacil.agendafacil_api.application.ports.out.agendamento.AtualizarStatusAgendamentoOutputPort;
import com.agendafacil.agendafacil_api.application.ports.out.agendamento.EnviarNotificacaoOutputPort;
import com.agendafacil.agendafacil_api.application.ports.out.agendamento.ObterAgendamentoPeloIdOutputPort;

import java.util.Set;

import static com.agendafacil.agendafacil_api.application.core.domain.StatusAgendamento.*;

public class AtualizarStatusAgendamentoUseCase implements AtualizarStatusAgendamentoInputPort {

    private final AtualizarStatusAgendamentoOutputPort atualizarStatusAgendamentoOutputPort;
    private final ObterAgendamentoPeloIdOutputPort obterAgendamentoPeloIdOutputPort;
    private final EnviarNotificacaoOutputPort enviarNotificacaoOutputPort;

    public AtualizarStatusAgendamentoUseCase(AtualizarStatusAgendamentoOutputPort atualizarStatusAgendamentoOutputPort,
                                             ObterAgendamentoPeloIdOutputPort obterAgendamentoPeloIdOutputPort,
                                             EnviarNotificacaoOutputPort enviarNotificacaoOutputPort
    ) {
        this.atualizarStatusAgendamentoOutputPort = atualizarStatusAgendamentoOutputPort;
        this.obterAgendamentoPeloIdOutputPort = obterAgendamentoPeloIdOutputPort;
        this.enviarNotificacaoOutputPort = enviarNotificacaoOutputPort;
    }

    @Override
    public Agendamento atualizar(Long id, StatusAgendamento novoStatus) {
        var agendamento = obterAgendamentoPeloIdOutputPort.obter(id);
        validarTransicao(agendamento.getStatusAgendamento(), novoStatus);

        agendamento.setStatusAgendamento(novoStatus);

        var agendamentoAtualizado = atualizarStatusAgendamentoOutputPort.atualizar(agendamento);

        if (novoStatus == CONFIRMADO) {
            enviarNotificacaoOutputPort.enviar(agendamentoAtualizado);
        }

        return agendamentoAtualizado;
    }

    private void validarTransicao(StatusAgendamento atual, StatusAgendamento novo) {
        boolean transicaoInvalida = switch (atual) {
            case PENDENTE -> !Set.of(CONFIRMADO, CANCELADO).contains(novo);
            case CONFIRMADO -> !Set.of(CONCLUIDO, CANCELADO).contains(novo);
            case CONCLUIDO, CANCELADO -> true;
        };

        if (transicaoInvalida) {
            throw new TransicaoStatusInvalidaException(atual, novo);
        }
    }
}
