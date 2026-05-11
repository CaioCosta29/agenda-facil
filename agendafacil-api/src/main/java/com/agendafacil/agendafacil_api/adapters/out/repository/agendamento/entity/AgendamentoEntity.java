package com.agendafacil.agendafacil_api.adapters.out.repository.agendamento.entity;

import com.agendafacil.agendafacil_api.adapters.out.repository.cliente.entity.ClienteEntity;
import com.agendafacil.agendafacil_api.adapters.out.repository.profissional.entity.ProfissionalEntity;
import com.agendafacil.agendafacil_api.application.core.domain.StatusAgendamento;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "agendamento")
public class AgendamentoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private ClienteEntity cliente;

    @ManyToOne
    @JoinColumn(name = "profissional_id")
    private ProfissionalEntity profissional;

    private LocalDateTime dataHora;

    @Enumerated(EnumType.STRING)
    private StatusAgendamento statusAgendamento;
}
