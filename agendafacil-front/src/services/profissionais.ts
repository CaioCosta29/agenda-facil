import api from "@/lib/api";
import type { Profissional, ProfissionalForm, AgendamentoAgenda } from "@/types";

export const profissionaisService = {
  listar: () => api.get<Profissional[]>("/profissionais").then((r) => r.data),
  criar: (data: ProfissionalForm) =>
    api.post<Profissional>("/profissionais", data).then((r) => r.data),
  atualizar: (id: number, data: ProfissionalForm) =>
    api.put<Profissional>(`/profissionais/${id}`, data).then((r) => r.data),
  deletar: (id: number) => api.delete(`/profissionais/${id}`),
  agenda: (id: number, data: string) =>
    api
      .get<AgendamentoAgenda[]>(`/profissionais/${id}/agenda`, { params: { data } })
      .then((r) => r.data),
};
