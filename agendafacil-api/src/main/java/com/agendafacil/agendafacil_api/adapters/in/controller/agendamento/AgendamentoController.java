package com.agendafacil.agendafacil_api.adapters.in.controller.agendamento;

import com.agendafacil.agendafacil_api.adapters.in.controller.agendamento.mapper.AgendamentoMapper;
import com.agendafacil.agendafacil_api.adapters.in.controller.agendamento.request.DadosAtualizacaoAgendamento;
import com.agendafacil.agendafacil_api.adapters.in.controller.agendamento.request.DadosCriacaoAgendamento;
import com.agendafacil.agendafacil_api.adapters.in.controller.agendamento.response.DadosDetalhamentoAgendamento;
import com.agendafacil.agendafacil_api.application.ports.in.agendamento.AtualizarStatusAgendamentoInputPort;
import com.agendafacil.agendafacil_api.application.ports.in.agendamento.FazerAgendamentoInputPort;
import com.agendafacil.agendafacil_api.application.ports.in.agendamento.ObterAgendamentoPeloIdInputPort;
import com.agendafacil.agendafacil_api.application.ports.in.agendamento.ObterAgendamentosInputPort;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/agendamentos")
@RequiredArgsConstructor
public class AgendamentoController {

    private final AgendamentoMapper agendamentoMapper;
    private final FazerAgendamentoInputPort fazerAgendamentoInputPort;
    private final ObterAgendamentosInputPort obterAgendamentosInputPort;
    private final AtualizarStatusAgendamentoInputPort atualizarStatusAgendamentoInputPort;
    private final ObterAgendamentoPeloIdInputPort obterAgendamentoPeloIdInputPort;

    @PostMapping
    public ResponseEntity<DadosDetalhamentoAgendamento> fazerAgendamento(@RequestBody @Valid DadosCriacaoAgendamento dadosCriacaoAgendamento) {
        var agendamentoSalvo = fazerAgendamentoInputPort.agendar(dadosCriacaoAgendamento.idCliente(), dadosCriacaoAgendamento.idProfissional(), dadosCriacaoAgendamento.dataHora());

        URI uri = URI.create("/agendamentos/" + agendamentoSalvo.getId());

        return ResponseEntity.created(uri).body(agendamentoMapper.toDetalhamento(agendamentoSalvo));
    }

    @GetMapping
    public ResponseEntity<List<DadosDetalhamentoAgendamento>> obterAgendamentos() {
        var agendamentos = obterAgendamentosInputPort.obter();

        var dadosDetalhamentoList = agendamentos.stream().map(agendamentoMapper::toDetalhamento).toList();

        return ResponseEntity.ok().body(dadosDetalhamentoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoAgendamento> obterAgendamentoPeloId(@PathVariable Long id) {
        var agendamento = obterAgendamentoPeloIdInputPort.obter(id);

        var dadosDetalhamento = agendamentoMapper.toDetalhamento(agendamento);

        return ResponseEntity.ok().body(dadosDetalhamento);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoAgendamento> atualizarStatusAgendamento(
            @RequestBody @Valid DadosAtualizacaoAgendamento dadosAtualizacaoAgendamento,
            @PathVariable Long id
    ) {
        var agendamento = atualizarStatusAgendamentoInputPort.atualizar(id, dadosAtualizacaoAgendamento.status());

        return ResponseEntity.ok().body(agendamentoMapper.toDetalhamento(agendamento));
    }
}
