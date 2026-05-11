"use client";

import { useState } from "react";
import { useQuery, useMutation, useQueryClient } from "@tanstack/react-query";
import { useForm } from "react-hook-form";
import { zodResolver } from "@hookform/resolvers/zod";
import { z } from "zod";
import { Plus, Pencil, Trash2, CalendarDays, X } from "lucide-react";
import { profissionaisService } from "@/services/profissionais";
import type { Profissional, ProfissionalForm, AgendamentoAgenda, Especialidade } from "@/types";
import Modal from "@/components/ui/Modal";
import ConfirmDialog from "@/components/ui/ConfirmDialog";
import Badge from "@/components/ui/Badge";
import LoadingSpinner from "@/components/ui/LoadingSpinner";
import ErrorMessage from "@/components/ui/ErrorMessage";
import EmptyState from "@/components/ui/EmptyState";

const especialidades: Especialidade[] = ["BARBEIRO", "CABELEREIRO", "ESTETICISTA", "MANICURE"];

const especialidadeLabel: Record<Especialidade, string> = {
  BARBEIRO: "Barbeiro",
  CABELEREIRO: "Cabeleireiro",
  ESTETICISTA: "Esteticista",
  MANICURE: "Manicure",
};

const schema = z.object({
  nome: z.string().min(2, "Nome obrigatório"),
  email: z.string().email("E-mail inválido"),
  especialidade: z.enum(["BARBEIRO", "CABELEREIRO", "ESTETICISTA", "MANICURE"]),
});

type FormData = z.infer<typeof schema>;

function formatDateTime(dataHora: string) {
  return new Date(dataHora).toLocaleString("pt-BR", {
    hour: "2-digit",
    minute: "2-digit",
  });
}

function todayISO() {
  return new Date().toISOString().slice(0, 10);
}

export default function ProfissionaisPage() {
  const qc = useQueryClient();
  const [modalOpen, setModalOpen] = useState(false);
  const [editing, setEditing] = useState<Profissional | null>(null);
  const [deleteTarget, setDeleteTarget] = useState<Profissional | null>(null);
  const [agendaTarget, setAgendaTarget] = useState<Profissional | null>(null);
  const [agendaDate, setAgendaDate] = useState(todayISO());

  const { data, isLoading, isError } = useQuery({
    queryKey: ["profissionais"],
    queryFn: profissionaisService.listar,
  });

  const agenda = useQuery({
    queryKey: ["agenda", agendaTarget?.id, agendaDate],
    queryFn: () => profissionaisService.agenda(agendaTarget!.id, agendaDate),
    enabled: !!agendaTarget,
  });

  const {
    register,
    handleSubmit,
    reset,
    formState: { errors },
  } = useForm<FormData>({ resolver: zodResolver(schema) });

  const criar = useMutation({
    mutationFn: (d: ProfissionalForm) => profissionaisService.criar(d),
    onSuccess: () => {
      qc.invalidateQueries({ queryKey: ["profissionais"] });
      closeModal();
    },
  });

  const atualizar = useMutation({
    mutationFn: ({ id, data }: { id: number; data: ProfissionalForm }) =>
      profissionaisService.atualizar(id, data),
    onSuccess: () => {
      qc.invalidateQueries({ queryKey: ["profissionais"] });
      closeModal();
    },
  });

  const deletar = useMutation({
    mutationFn: (id: number) => profissionaisService.deletar(id),
    onSuccess: () => {
      qc.invalidateQueries({ queryKey: ["profissionais"] });
      setDeleteTarget(null);
    },
  });

  function openCreate() {
    setEditing(null);
    reset({ nome: "", email: "", especialidade: "BARBEIRO" });
    setModalOpen(true);
  }

  function openEdit(p: Profissional) {
    setEditing(p);
    reset({ nome: p.nome, email: p.email, especialidade: p.especialidade });
    setModalOpen(true);
  }

  function closeModal() {
    setModalOpen(false);
    setEditing(null);
    reset();
  }

  function onSubmit(data: FormData) {
    if (editing) {
      atualizar.mutate({ id: editing.id, data });
    } else {
      criar.mutate(data);
    }
  }

  const isSaving = criar.isPending || atualizar.isPending;

  return (
    <div className="space-y-4">
      <div className="flex items-center justify-between">
        <p className="text-sm text-gray-500">
          {data ? `${data.length} profissional(is) cadastrado(s)` : ""}
        </p>
        <button
          onClick={openCreate}
          className="flex items-center gap-2 rounded-lg bg-indigo-600 px-4 py-2 text-sm font-medium text-white hover:bg-indigo-700"
        >
          <Plus size={16} />
          Novo Profissional
        </button>
      </div>

      <div className="bg-white rounded-xl border border-gray-100 shadow-sm overflow-hidden">
        {isLoading && <LoadingSpinner />}
        {isError && <ErrorMessage />}
        {!isLoading && !isError && data?.length === 0 && (
          <EmptyState message="Nenhum profissional cadastrado." />
        )}
        {!isLoading && !isError && data && data.length > 0 && (
          <div className="overflow-x-auto">
            <table className="w-full text-sm">
              <thead>
                <tr className="bg-gray-50 text-left">
                  <th className="px-6 py-3 font-medium text-gray-500">Nome</th>
                  <th className="px-6 py-3 font-medium text-gray-500">E-mail</th>
                  <th className="px-6 py-3 font-medium text-gray-500">Especialidade</th>
                  <th className="px-6 py-3 font-medium text-gray-500 text-right">Ações</th>
                </tr>
              </thead>
              <tbody className="divide-y divide-gray-50">
                {data.map((p: Profissional) => (
                  <tr key={p.id} className="hover:bg-gray-50/50">
                    <td className="px-6 py-3 text-gray-900 font-medium">{p.nome}</td>
                    <td className="px-6 py-3 text-gray-600">{p.email}</td>
                    <td className="px-6 py-3 text-gray-600">
                      {especialidadeLabel[p.especialidade]}
                    </td>
                    <td className="px-6 py-3 text-right">
                      <div className="flex items-center justify-end gap-2">
                        <button
                          onClick={() => setAgendaTarget(p)}
                          title="Ver agenda"
                          className="rounded-lg p-1.5 text-gray-500 hover:bg-gray-100 hover:text-emerald-600"
                        >
                          <CalendarDays size={15} />
                        </button>
                        <button
                          onClick={() => openEdit(p)}
                          className="rounded-lg p-1.5 text-gray-500 hover:bg-gray-100 hover:text-indigo-600"
                        >
                          <Pencil size={15} />
                        </button>
                        <button
                          onClick={() => setDeleteTarget(p)}
                          className="rounded-lg p-1.5 text-gray-500 hover:bg-red-50 hover:text-red-600"
                        >
                          <Trash2 size={15} />
                        </button>
                      </div>
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        )}
      </div>

      {/* Agenda drawer */}
      {agendaTarget && (
        <div className="fixed inset-0 z-50 flex">
          <div className="absolute inset-0 bg-black/40" onClick={() => setAgendaTarget(null)} />
          <div className="relative ml-auto bg-white w-full max-w-md h-full shadow-xl flex flex-col">
            <div className="flex items-center justify-between px-6 py-4 border-b border-gray-100">
              <div>
                <h2 className="font-semibold text-gray-900">Agenda</h2>
                <p className="text-sm text-gray-500">{agendaTarget.nome}</p>
              </div>
              <button
                onClick={() => setAgendaTarget(null)}
                className="rounded-lg p-1.5 text-gray-500 hover:bg-gray-100"
              >
                <X size={18} />
              </button>
            </div>
            <div className="px-6 py-4 border-b border-gray-100">
              <label className="block text-sm font-medium text-gray-700 mb-1">Data</label>
              <input
                type="date"
                value={agendaDate}
                onChange={(e) => setAgendaDate(e.target.value)}
                className="w-full rounded-lg border border-gray-300 px-3 py-2 text-sm focus:outline-none focus:ring-2 focus:ring-indigo-500"
              />
            </div>
            <div className="flex-1 overflow-y-auto px-6 py-4">
              {agenda.isLoading && <LoadingSpinner />}
              {agenda.isError && <ErrorMessage message="Erro ao carregar agenda." />}
              {!agenda.isLoading && !agenda.isError && agenda.data?.length === 0 && (
                <EmptyState message="Sem agendamentos neste dia." />
              )}
              {agenda.data && agenda.data.length > 0 && (
                <ul className="space-y-3">
                  {agenda.data.map((a: AgendamentoAgenda) => (
                    <li
                      key={a.id}
                      className="flex items-center justify-between rounded-lg border border-gray-100 px-4 py-3"
                    >
                      <div>
                        <p className="font-medium text-gray-900 text-sm">{a.nomeCliente}</p>
                        <p className="text-xs text-gray-500 mt-0.5">
                          {formatDateTime(a.dataHora)}
                        </p>
                      </div>
                      <Badge status={a.status} />
                    </li>
                  ))}
                </ul>
              )}
            </div>
          </div>
        </div>
      )}

      <Modal
        open={modalOpen}
        title={editing ? "Editar Profissional" : "Novo Profissional"}
        onClose={closeModal}
      >
        <form onSubmit={handleSubmit(onSubmit)} className="space-y-4">
          <div>
            <label className="block text-sm font-medium text-gray-700 mb-1">Nome</label>
            <input
              {...register("nome")}
              className="w-full rounded-lg border border-gray-300 px-3 py-2 text-sm focus:outline-none focus:ring-2 focus:ring-indigo-500"
              placeholder="Nome completo"
            />
            {errors.nome && <p className="mt-1 text-xs text-red-600">{errors.nome.message}</p>}
          </div>
          <div>
            <label className="block text-sm font-medium text-gray-700 mb-1">E-mail</label>
            <input
              {...register("email")}
              type="email"
              className="w-full rounded-lg border border-gray-300 px-3 py-2 text-sm focus:outline-none focus:ring-2 focus:ring-indigo-500"
              placeholder="email@exemplo.com"
            />
            {errors.email && <p className="mt-1 text-xs text-red-600">{errors.email.message}</p>}
          </div>
          <div>
            <label className="block text-sm font-medium text-gray-700 mb-1">Especialidade</label>
            <select
              {...register("especialidade")}
              className="w-full rounded-lg border border-gray-300 px-3 py-2 text-sm focus:outline-none focus:ring-2 focus:ring-indigo-500"
            >
              {especialidades.map((e) => (
                <option key={e} value={e}>
                  {especialidadeLabel[e]}
                </option>
              ))}
            </select>
          </div>
          {(criar.isError || atualizar.isError) && (
            <ErrorMessage message="Erro ao salvar. Verifique os dados e tente novamente." />
          )}
          <div className="flex justify-end gap-3 pt-2">
            <button
              type="button"
              onClick={closeModal}
              className="px-4 py-2 text-sm font-medium text-gray-700 bg-white border border-gray-300 rounded-lg hover:bg-gray-50"
            >
              Cancelar
            </button>
            <button
              type="submit"
              disabled={isSaving}
              className="px-4 py-2 text-sm font-medium text-white bg-indigo-600 rounded-lg hover:bg-indigo-700 disabled:opacity-50"
            >
              {isSaving ? "Salvando..." : "Salvar"}
            </button>
          </div>
        </form>
      </Modal>

      <ConfirmDialog
        open={!!deleteTarget}
        title="Excluir profissional"
        description={`Deseja excluir o profissional "${deleteTarget?.nome}"? Esta ação não pode ser desfeita.`}
        onConfirm={() => deleteTarget && deletar.mutate(deleteTarget.id)}
        onCancel={() => setDeleteTarget(null)}
        loading={deletar.isPending}
      />
    </div>
  );
}
