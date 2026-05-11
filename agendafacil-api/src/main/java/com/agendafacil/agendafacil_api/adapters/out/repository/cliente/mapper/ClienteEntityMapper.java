package com.agendafacil.agendafacil_api.adapters.out.repository.cliente.mapper;

import com.agendafacil.agendafacil_api.adapters.out.repository.cliente.entity.ClienteEntity;
import com.agendafacil.agendafacil_api.application.core.domain.Cliente;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClienteEntityMapper {

    ClienteEntity toEntity(Cliente cliente);

    Cliente toDomain(ClienteEntity clienteEntity);
}
