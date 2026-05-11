"use client";

import { useState } from "react";
import { useQuery, useMutation, useQueryClient } from "@tanstack/react-query";
import { useForm } from "react-hook-form";
import { zodResolver } from "@hookform/resolvers/zod";
import { z } from "zod";
import { Plus, RefreshCw } from "lucide-react";
import { agendamentosService } from "@/services/agendamentos";
import { clientesService } from "@/services/clientes";
import { profissionaisService } from "@/services/profissionais";
import type { Agendamento, AgendamentoForm, StatusAgendamento, Cliente, Profissional } from "@/types";
import Modal from "@/components/ui/Modal";
import Badge from "@/components/ui/Badge";
import LoadingSpinner from "@/components/ui/LoadingSpinner";
import ErrorMessage from "@/components/ui/ErrorMessage";
import EmptyState from "@/components/ui/EmptyState";

const statusOptions: StatusAgendamento[] = ["PENDENTE", "CONFIRMADO", "CONCLUIDO", "CANCELADO"];

const statusLabel: Record<StatusAgendamento, string> = {
  PENDENTE: "Pendente",
  CONFIRMADO: "Confirmado",
  CONCLUIDO: "Concluído",
  CANCELADO: "Cancelado",
};

const schema = z.object({
  idCliente: z.string().refine((v) => v !== "" && v !== "0", "Selecione um cliente"),
  idProfissional: z.string().refine((v) => v !== "" && v !== "0", "Selecione um profissional"),
  dataHora: z.string().min(1, "Data e hora obrigatórias"),
});

type FormData = z.infer<typeof schema>;

function formatDateTime(dataHora: string) {
  return new Date(dataHora).toLocaleString("pt-BR", {
    day: "2-digit",
    month: "2-digit",
    year: "numeric",
    hour: "2-digit",
    minute: "2-digit",
  });
}

export default function AgendamentosPage() {
  const qc = useQueryClient();
  const [modalOpen, setModalOpen] = useState(false);
  const [statusTarget, setStatusTarget] = useState<Agendamento | null>(null);
  const [novoStatus, setNovoStatus] = useState<StatusAgendamento>("PENDENTE");

  const { data, isLoading, isError } = useQuery({
    queryKey: ["agendamentos"],
    queryFn: agendamentosService.listar,
  });

  const clientes = useQuery({ queryKey: ["clientes"], queryFn: clientesService.listar });
  const profissionais = useQuery({
    queryKey: ["profissionais"],
    queryFn: profissionaisService.listar,
  });

  const {
    register,
    handleSubmit,
    reset,
    formState: { errors },
  } = useForm<FormData>({ resolver: zodResolver(schema) });

  const criar = useMutation({
    mutationFn: (d: AgendamentoForm) => agendamentosService.criar(d),
    onSuccess: () => {
      qc.invalidateQueries({ queryKey: ["agendamentos"] });
      setModalOpen(false);
      reset();
    },
  });

  const atualizarStatus = useMutation({
    mutationFn: ({ id, status }: { id: number; status: StatusAgendamento }) =>
      agendamentosService.atualizarStatus(id, status),
    onSuccess: () => {
      qc.invalidateQueries({ queryKey: ["agendamentos"] });
      setStatusTarget(null);
    },
  });

  function openCreate() {
    reset({ idCliente: "", idProfissional: "", dataHora: "" });
    setModalOpen(true);
  }

  function openStatus(a: Agendamento) {
    setNovoStatus(a.status);
    setStatusTarget(a);
  }

  function onSubmit(data: FormData) {
    criar.mutate({
      idCliente: parseInt(data.idCliente, 10),
      idProfissional: parseInt(data.idProfissional, 10),
      dataHora: data.dataHora,
    });
  }

  return (
    <div className="space-y-4">
      <div className="flex items-center justify-between">
        <p className="text-sm text-gray-500">
          {data ? `${data.length} agendamento(s)` : ""}
        </p>
        <button
          onClick={openCreate}
          className="flex items-center gap-2 rounded-lg bg-indigo-600 px-4 py-2 text-sm font-medium text-white hover:bg-indigo-700"
        >
          <Plus size={16} />
          Novo Agendamento
        </button>
      </div>

      <div className="bg-white rounded-xl border border-gray-100 shadow-sm overflow-hidden">
        {isLoading && <LoadingSpinner />}
        {isError && <ErrorMessage />}
        {!isLoading && !isError && data?.length === 0 && (
          <EmptyState message="Nenhum agendamento encontrado." />
        )}
        {!isLoading && !isError && data && data.length > 0 && (
          <div className="overflow-x-auto">
            <table className="w-full text-sm">
              <thead>
                <tr className="bg-gray-50 text-left">
                  <th className="px-6 py-3 font-medium text-gray-500">Cliente</th>
                  <th className="px-6 py-3 font-medium text-gray-500">Profissional</th>
                  <th className="px-6 py-3 font-medium text-gray-500">Data / Hora</th>
                  <th className="px-6 py-3 font-medium text-gray-500">Status</th>
                  <th className="px-6 py-3 font-medium text-gray-500 text-right">Ações</th>
                </tr>
              </thead>
              <tbody className="divide-y divide-gray-50">
                {data.map((a: Agendamento) => (
                  <tr key={a.id} className="hover:bg-gray-50/50">
                    <td className="px-6 py-3 text-gray-900 font-medium">{a.nomeCliente}</td>
                    <td className="px-6 py-3 text-gray-600">{a.nomeProfissional}</td>
                    <td className="px-6 py-3 text-gray-600">{formatDateTime(a.dataHora)}</td>
                    <td className="px-6 py-3">
                      <Badge status={a.status} />
                    </td>
                    <td className="px-6 py-3 text-right">
                      <button
                        onClick={() => openStatus(a)}
                        title="Alterar status"
                        className="rounded-lg p-1.5 text-gray-500 hover:bg-gray-100 hover:text-indigo-600"
                      >
                        <RefreshCw size={15} />
                      </button>
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        )}
      </div>

      {/* Modal criar agendamento */}
      <Modal open={modalOpen} title="Novo Agendamento" onClose={() => setModalOpen(false)}>
        <form onSubmit={handleSubmit(onSubmit)} className="space-y-4">
          <div>
            <label className="block text-sm font-medium text-gray-700 mb-1">Cliente</label>
            <select
              {...register("idCliente")}
              className="w-full rounded-lg border border-gray-300 px-3 py-2 text-sm focus:outline-none focus:ring-2 focus:ring-indigo-500"
            >
              <option value="">Selecione...</option>
              {clientes.data?.map((c: Cliente) => (
                <option key={c.id} value={c.id}>
                  {c.nome}
                </option>
              ))}
            </select>
            {errors.idCliente && (
              <p className="mt-1 text-xs text-red-600">{errors.idCliente.message}</p>
            )}
          </div>
          <div>
            <label className="block text-sm font-medium text-gray-700 mb-1">Profissional</label>
            <select
              {...register("idProfissional")}
              className="w-full rounded-lg border border-gray-300 px-3 py-2 text-sm focus:outline-none focus:ring-2 focus:ring-indigo-500"
            >
              <option value="">Selecione...</option>
              {profissionais.data?.map((p: Profissional) => (
                <option key={p.id} value={p.id}>
                  {p.nome}
                </option>
              ))}
            </select>
            {errors.idProfissional && (
              <p className="mt-1 text-xs text-red-600">{errors.idProfissional.message}</p>
            )}
          </div>
          <div>
            <label className="block text-sm font-medium text-gray-700 mb-1">Data e Hora</label>
            <input
              {...register("dataHora")}
              type="datetime-local"
              className="w-full rounded-lg border border-gray-300 px-3 py-2 text-sm focus:outline-none focus:ring-2 focus:ring-indigo-500"
            />
            {errors.dataHora && (
              <p className="mt-1 text-xs text-red-600">{errors.dataHora.message}</p>
            )}
          </div>
          {criar.isError && (
            <ErrorMessage message="Erro ao criar agendamento. Verifique os dados e tente novamente." />
          )}
          <div className="flex justify-end gap-3 pt-2">
            <button
              type="button"
              onClick={() => setModalOpen(false)}
              className="px-4 py-2 text-sm font-medium text-gray-700 bg-white border border-gray-300 rounded-lg hover:bg-gray-50"
            >
              Cancelar
            </button>
            <button
              type="submit"
              disabled={criar.isPending}
              className="px-4 py-2 text-sm font-medium text-white bg-indigo-600 rounded-lg hover:bg-indigo-700 disabled:opacity-50"
            >
              {criar.isPending ? "Salvando..." : "Salvar"}
            </button>
          </div>
        </form>
      </Modal>

      {/* Modal atualizar status */}
      <Modal
        open={!!statusTarget}
        title="Atualizar Status"
        onClose={() => setStatusTarget(null)}
      >
        {statusTarget && (
          <div className="space-y-4">
            <p className="text-sm text-gray-600">
              Agendamento de{" "}
              <span className="font-medium text-gray-900">{statusTarget.nomeCliente}</span> com{" "}
              <span className="font-medium text-gray-900">{statusTarget.nomeProfissional}</span>
              {" — "}
              {formatDateTime(statusTarget.dataHora)}
            </p>
            <div>
              <label className="block text-sm font-medium text-gray-700 mb-1">Novo status</label>
              <select
                value={novoStatus}
                onChange={(e) => setNovoStatus(e.target.value as StatusAgendamento)}
                className="w-full rounded-lg border border-gray-300 px-3 py-2 text-sm focus:outline-none focus:ring-2 focus:ring-indigo-500"
              >
                {statusOptions.map((s) => (
                  <option key={s} value={s}>
                    {statusLabel[s]}
                  </option>
                ))}
              </select>
            </div>
            {atualizarStatus.isError && (
              <ErrorMessage message="Erro ao atualizar status." />
            )}
            <div className="flex justify-end gap-3 pt-2">
              <button
                onClick={() => setStatusTarget(null)}
                className="px-4 py-2 text-sm font-medium text-gray-700 bg-white border border-gray-300 rounded-lg hover:bg-gray-50"
              >
                Cancelar
              </button>
              <button
                onClick={() =>
                  atualizarStatus.mutate({ id: statusTarget.id, status: novoStatus })
                }
                disabled={atualizarStatus.isPending}
                className="px-4 py-2 text-sm font-medium text-white bg-indigo-600 rounded-lg hover:bg-indigo-700 disabled:opacity-50"
              >
                {atualizarStatus.isPending ? "Salvando..." : "Confirmar"}
              </button>
            </div>
          </div>
        )}
      </Modal>
    </div>
  );
}
