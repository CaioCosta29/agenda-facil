import api from "@/lib/api";
import type { Agendamento, AgendamentoForm, StatusAgendamento } from "@/types";

export const agendamentosService = {
  listar: () => api.get<Agendamento[]>("/agendamentos").then((r) => r.data),
  buscarPorId: (id: number) =>
    api.get<Agendamento>(`/agendamentos/${id}`).then((r) => r.data),
  criar: (data: AgendamentoForm) =>
    api.post<Agendamento>("/agendamentos", data).then((r) => r.data),
  atualizarStatus: (id: number, status: StatusAgendamento) =>
    api.put<Agendamento>(`/agendamentos/${id}`, { status }).then((r) => r.data),
};
