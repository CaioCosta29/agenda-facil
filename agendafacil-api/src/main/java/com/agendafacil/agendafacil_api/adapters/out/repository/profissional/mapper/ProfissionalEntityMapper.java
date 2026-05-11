package com.agendafacil.agendafacil_api.adapters.out.repository.profissional.mapper;

import com.agendafacil.agendafacil_api.adapters.out.repository.profissional.entity.ProfissionalEntity;
import com.agendafacil.agendafacil_api.application.core.domain.Profissional;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProfissionalEntityMapper {

    ProfissionalEntity toEntity(Profissional profissional);

    Profissional toDomain(ProfissionalEntity profissionalEntity);
}
