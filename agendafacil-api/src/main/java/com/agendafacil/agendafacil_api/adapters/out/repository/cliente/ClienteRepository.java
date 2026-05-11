package com.agendafacil.agendafacil_api.adapters.out.repository.cliente;

import com.agendafacil.agendafacil_api.adapters.out.repository.cliente.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<ClienteEntity, Long> {
}
