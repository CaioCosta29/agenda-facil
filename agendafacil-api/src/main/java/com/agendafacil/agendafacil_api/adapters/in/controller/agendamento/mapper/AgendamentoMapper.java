package com.agendafacil.agendafacil_api.adapters.in.controller.agendamento.mapper;

import com.agendafacil.agendafacil_api.adapters.in.controller.agendamento.response.DadosDetalhamentoAgendamento;
import com.agendafacil.agendafacil_api.application.core.domain.Agendamento;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AgendamentoMapper {

    @Mapping(source = "cliente.id", target = "idCliente")
    @Mapping(source = "cliente.nome", target = "nomeCliente")
    @Mapping(source = "profissional.id", target = "idProfissional")
    @Mapping(source = "profissional.nome", target = "nomeProfissional")
    @Mapping(source = "statusAgendamento", target = "status")
    DadosDetalhamentoAgendamento toDetalhamento(Agendamento agendamento);

}
