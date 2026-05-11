package com.agendafacil.agendafacil_api.application.core.usecase.agendamento;

import com.agendafacil.agendafacil_api.application.core.domain.Agendamento;
import com.agendafacil.agendafacil_api.application.core.domain.StatusAgendamento;
import com.agendafacil.agendafacil_api.application.core.exception.AgendamentoConflitanteException;
import com.agendafacil.agendafacil_api.application.core.usecase.agendamento.validacao.ValidadorAgendamento;
import com.agendafacil.agendafacil_api.application.ports.out.agendamento.FazerAgendamentoOutputPort;
import com.agendafacil.agendafacil_api.application.ports.out.cliente.ColetarClientePeloIdOutputPort;
import com.agendafacil.agendafacil_api.application.ports.out.profissional.ColetarProfissionalPeloIdOutputPort;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class FazerAgendamentoUseCaseTest {


    @Test
    void cenario01() {
        // cenario feliz

        ColetarClientePeloIdOutputPort coletarClientePort = Mockito.mock(ColetarClientePeloIdOutputPort.class);
        ColetarProfissionalPeloIdOutputPort coletarProfissionalPeloIdOutputPort = Mockito.mock(ColetarProfissionalPeloIdOutputPort.class);
        FazerAgendamentoOutputPort fazerAgendamentoOutputPort = Mockito.mock(FazerAgendamentoOutputPort.class);

        var agendamentoFalso = new Agendamento();
        agendamentoFalso.setStatusAgendamento(StatusAgendamento.PENDENTE);
        when(fazerAgendamentoOutputPort.agendar(any())).thenReturn(agendamentoFalso);


        FazerAgendamentoUseCase fazerAgendamentoUseCase = new FazerAgendamentoUseCase(coletarClientePort, coletarProfissionalPeloIdOutputPort, fazerAgendamentoOutputPort, List.of());
        var agendamento = fazerAgendamentoUseCase.agendar(1L,1L , LocalDateTime.of(2025, 10, 10, 14, 0));

        Assertions.assertEquals(StatusAgendamento.PENDENTE, agendamento.getStatusAgendamento());
    }

    @Test
    void cenario02() {
        // quando fica no validador

        ValidadorAgendamento validador = Mockito.mock(ValidadorAgendamento.class);
        var conflitoFalso = new AgendamentoConflitanteException(1L, LocalDateTime.of(2025, 10, 10, 14, 0));
        when(validador.validar(alguma coisa)).doThrow(new AgendamentoConflitanteException(1L, dataHora))
                .when(validador).validar(any(), any());

        validador.validar(1L, LocalDateTime.of(2025, 10, 10, 14, 0));
    }

}