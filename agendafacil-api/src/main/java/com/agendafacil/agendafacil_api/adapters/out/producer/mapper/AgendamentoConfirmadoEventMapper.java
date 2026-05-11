package com.agendafacil.agendafacil_api.adapters.out.producer.mapper;

import com.agendafacil.agendafacil_api.adapters.out.producer.dto.AgendamentoConfirmadoEvent;
import com.agendafacil.agendafacil_api.application.core.domain.Agendamento;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AgendamentoConfirmadoEventMapper {

    @Mapping(source = "id", target = "agendamentoId")
    @Mapping(source = "cliente.nome", target = "nomeCliente")
    @Mapping(source = "cliente.email", target = "emailCliente")
    @Mapping(source = "profissional.nome", target = "nomeProfissional")
    @Mapping(source = "profissional.email", target = "emailProfissional")
    AgendamentoConfirmadoEvent toEvent(Agendamento agendamento);
}
