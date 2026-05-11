package com.agendafacil.agendafacil_api.adapters.in.controller.cliente.mapper;

import com.agendafacil.agendafacil_api.adapters.in.controller.cliente.request.DadosAtualizacaoCliente;
import com.agendafacil.agendafacil_api.adapters.in.controller.cliente.request.DadosCadastroCliente;
import com.agendafacil.agendafacil_api.adapters.in.controller.cliente.response.DadosDetalhamentoCliente;
import com.agendafacil.agendafacil_api.application.core.domain.Cliente;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    Cliente toDomain(DadosCadastroCliente dadosCadastroCliente);

    DadosDetalhamentoCliente toDetalhamento(Cliente cliente);

    Cliente fromDadosAtualizaoToDomain(DadosAtualizacaoCliente dadosAtualizacaoCliente);
}
