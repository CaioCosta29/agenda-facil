package com.agendafacil.agendafacil_api.application.core.domain;

import java.time.LocalDateTime;

public class Agendamento {
    private Long id;
    private Cliente cliente;
    private Profissional profissional;
    private LocalDateTime dataHora;
    private StatusAgendamento statusAgendamento;

    public Agendamento() {
    }

    public Agendamento(Long id, Cliente cliente, Profissional profissional, LocalDateTime dataHora, StatusAgendamento statusAgendamento) {
        this.id = id;
        this.cliente = cliente;
        this.profissional = profissional;
        this.dataHora = dataHora;
        this.statusAgendamento = statusAgendamento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Profissional getProfissional() {
        return profissional;
    }

    public void setProfissional(Profissional profissional) {
        this.profissional = profissional;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public StatusAgendamento getStatusAgendamento() {
        return statusAgendamento;
    }

    public void setStatusAgendamento(StatusAgendamento statusAgendamento) {
        this.statusAgendamento = statusAgendamento;
    }
}
