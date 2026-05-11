export type Especialidade = "BARBEIRO" | "CABELEREIRO" | "ESTETICISTA" | "MANICURE";

export type StatusAgendamento = "PENDENTE" | "CONFIRMADO" | "CONCLUIDO" | "CANCELADO";

export interface Cliente {
  id: number;
  nome: string;
  email: string;
  telefone: string;
}

export interface Profissional {
  id: number;
  nome: string;
  email: string;
  especialidade: Especialidade;
}

export interface Agendamento {
  id: number;
  idCliente: number;
  nomeCliente: string;
  idProfissional: number;
  nomeProfissional: string;
  dataHora: string;
  status: StatusAgendamento;
}

export interface AgendamentoAgenda {
  id: number;
  idCliente: number;
  nomeCliente: string;
  idProfissional: number;
  nomeProfissional: string;
  dataHora: string;
  status: StatusAgendamento;
}

export interface ClienteForm {
  nome: string;
  email: string;
  telefone: string;
}

export interface ProfissionalForm {
  nome: string;
  email: string;
  especialidade: Especialidade;
}

export interface AgendamentoForm {
  idCliente: number;
  idProfissional: number;
  dataHora: string;
}
