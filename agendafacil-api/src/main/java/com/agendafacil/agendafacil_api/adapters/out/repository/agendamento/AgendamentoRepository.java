package com.agendafacil.agendafacil_api.adapters.out.repository.agendamento;

import com.agendafacil.agendafacil_api.adapters.out.repository.agendamento.entity.AgendamentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface AgendamentoRepository extends JpaRepository<AgendamentoEntity, Long> {

    boolean existsByProfissionalIdAndDataHora(Long profissionalId, LocalDateTime dataHora);

    List<AgendamentoEntity> findByProfissionalIdAndDataHoraBetween(
            Long profissionalId,
            LocalDateTime inicio,
            LocalDateTime fim
    );
}
