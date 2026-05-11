package com.agendafacil.agendafacil_api.adapters.out.repository.agendamento.mapper;

import com.agendafacil.agendafacil_api.adapters.out.repository.agendamento.entity.AgendamentoEntity;
import com.agendafacil.agendafacil_api.application.core.domain.Agendamento;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AgendamentoEntityMapper {

    AgendamentoEntity toEntity(Agendamento agendamento);

    Agendamento toDomain(AgendamentoEntity agendamentoEntity);
}
