package com.agendafacil.agendafacil_api.adapters.in.controller.profissional;

import com.agendafacil.agendafacil_api.adapters.in.controller.agendamento.mapper.AgendamentoMapper;
import com.agendafacil.agendafacil_api.adapters.in.controller.agendamento.response.DadosDetalhamentoAgendamento;
import com.agendafacil.agendafacil_api.adapters.in.controller.profissional.mapper.ProfissionalMapper;
import com.agendafacil.agendafacil_api.adapters.in.controller.profissional.request.DadosAtualizacaoProfissional;
import com.agendafacil.agendafacil_api.adapters.in.controller.profissional.request.DadosCadastroProfissional;
import com.agendafacil.agendafacil_api.adapters.in.controller.profissional.response.DadosDetalhamentoProfissional;
import com.agendafacil.agendafacil_api.application.core.domain.Profissional;
import com.agendafacil.agendafacil_api.application.ports.in.agendamento.ObterAgendaProfissionalPelaDataInputPort;
import com.agendafacil.agendafacil_api.application.ports.in.profissional.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/profissionais")
@RequiredArgsConstructor
public class ProfissionalController {

    private final ProfissionalMapper profissionalMapper;
    private final CadastrarProfissionalInputPort cadastrarProfissionalInputPort;
    private final ColetarProfissionaisInputPort coletarProfissionaisInputPort;
    private final AtualizarProfissionalInputPort atualizarProfissionalInputPort;
    private final DeletarProfissionalInputPort deletarProfissionalInputPort;
    private final AgendamentoMapper agendamentoMapper;
    private final ObterAgendaProfissionalPelaDataInputPort obterAgendaProfissionalPelaDataInputPort;

    @PostMapping
    public ResponseEntity<DadosDetalhamentoProfissional> cadastrarProfissional(@RequestBody @Valid DadosCadastroProfissional dadosCadastroProfissional) {
        var profissional = profissionalMapper.toDomain(dadosCadastroProfissional);
        var profissionalSalvo = cadastrarProfissionalInputPort.cadastrar(profissional);

        URI uri = URI.create("/profissionais/" + profissionalSalvo.getId());

        return ResponseEntity.created(uri).body(profissionalMapper.toDetalhamento(profissionalSalvo));
    }

    @GetMapping
    public ResponseEntity<List<DadosDetalhamentoProfissional>> coletarProfissionais() {
        var profissionais = coletarProfissionaisInputPort.coletar();

        var dadosDetalhamento = profissionais.stream().map(profissionalMapper::toDetalhamento).toList();

        return ResponseEntity.ok().body(dadosDetalhamento);
    }

    @GetMapping("/{id}/agenda")
    public ResponseEntity<List<DadosDetalhamentoAgendamento>> obterAgendaProfissionalPelaData(
            @PathVariable Long id,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data
    ) {
        var agendamentos = obterAgendaProfissionalPelaDataInputPort.obter(id, data);

        return ResponseEntity.ok(agendamentos.stream()
                .map(agendamentoMapper::toDetalhamento)
                .toList());
    }

    @PutMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoProfissional> atualizarProfissional(
            @RequestBody @Valid DadosAtualizacaoProfissional dadosAtualizacaoProfissional,
            @PathVariable Long id
    ) {
        Profissional profissional = profissionalMapper.fromDadosAtualizaoToDomain(dadosAtualizacaoProfissional);

        var profissionalAtualizado = atualizarProfissionalInputPort.atualizar(id, profissional);

        return ResponseEntity.ok().body(profissionalMapper.toDetalhamento(profissionalAtualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProfissional(@PathVariable Long id) {
        deletarProfissionalInputPort.deletar(id);

        return ResponseEntity.noContent().build();
    }
}
