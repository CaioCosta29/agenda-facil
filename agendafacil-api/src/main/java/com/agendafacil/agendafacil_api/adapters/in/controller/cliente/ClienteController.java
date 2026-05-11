package com.agendafacil.agendafacil_api.adapters.in.controller.cliente;

import com.agendafacil.agendafacil_api.adapters.in.controller.cliente.mapper.ClienteMapper;
import com.agendafacil.agendafacil_api.adapters.in.controller.cliente.request.DadosAtualizacaoCliente;
import com.agendafacil.agendafacil_api.adapters.in.controller.cliente.request.DadosCadastroCliente;
import com.agendafacil.agendafacil_api.adapters.in.controller.cliente.response.DadosDetalhamentoCliente;
import com.agendafacil.agendafacil_api.application.core.domain.Cliente;
import com.agendafacil.agendafacil_api.application.ports.in.cliente.AtualizarClienteInputPort;
import com.agendafacil.agendafacil_api.application.ports.in.cliente.CadastrarClienteInputPort;
import com.agendafacil.agendafacil_api.application.ports.in.cliente.ColetarClientesInputPort;
import com.agendafacil.agendafacil_api.application.ports.in.cliente.DeletarClienteInputPort;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteMapper clienteMapper;
    private final CadastrarClienteInputPort cadastrarClienteInputPort;
    private final ColetarClientesInputPort coletarClientesInputPort;
    private final AtualizarClienteInputPort atualizarClienteInputPort;
    private final DeletarClienteInputPort deletarClienteInputPort;

    @PostMapping()
    public ResponseEntity<DadosDetalhamentoCliente> cadastrarCliente(@RequestBody @Valid DadosCadastroCliente dadosCadastroCliente) {
        var cliente = clienteMapper.toDomain(dadosCadastroCliente);
        var clienteSalvo = cadastrarClienteInputPort.cadastrar(cliente);

        URI uri = URI.create("/cliente/" + clienteSalvo.getId());

        return ResponseEntity.created(uri).body(clienteMapper.toDetalhamento(clienteSalvo));
    }

    @GetMapping
    public ResponseEntity<List<DadosDetalhamentoCliente>> coletarClientes() {
        var clientes = coletarClientesInputPort.coletar();

        var dadosDetalhamento = clientes.stream().map(cliente -> clienteMapper.toDetalhamento(cliente)).toList();

        return ResponseEntity.ok().body(dadosDetalhamento);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoCliente> atualizarCliente(
            @RequestBody @Valid DadosAtualizacaoCliente dadosAtualizacaoCliente,
            @PathVariable Long id
    ) {
        Cliente cliente = clienteMapper.fromDadosAtualizaoToDomain(dadosAtualizacaoCliente);

        var clienteAtualizado = atualizarClienteInputPort.atualizar(id, cliente);

        return ResponseEntity.ok().body(clienteMapper.toDetalhamento(clienteAtualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCliente(@PathVariable Long id) {
        deletarClienteInputPort.deletar(id);

        return ResponseEntity.noContent().build();
    }
}
