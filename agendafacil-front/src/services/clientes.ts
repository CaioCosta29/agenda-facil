import api from "@/lib/api";
import type { Cliente, ClienteForm } from "@/types";

export const clientesService = {
  listar: () => api.get<Cliente[]>("/clientes").then((r) => r.data),
  criar: (data: ClienteForm) => api.post<Cliente>("/clientes", data).then((r) => r.data),
  atualizar: (id: number, data: ClienteForm) =>
    api.put<Cliente>(`/clientes/${id}`, data).then((r) => r.data),
  deletar: (id: number) => api.delete(`/clientes/${id}`),
};
