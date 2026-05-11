"use client";

import { useState } from "react";
import { useQuery, useMutation, useQueryClient } from "@tanstack/react-query";
import { useForm } from "react-hook-form";
import { zodResolver } from "@hookform/resolvers/zod";
import { z } from "zod";
import { Plus, Pencil, Trash2 } from "lucide-react";
import { clientesService } from "@/services/clientes";
import type { Cliente, ClienteForm } from "@/types";
import Modal from "@/components/ui/Modal";
import ConfirmDialog from "@/components/ui/ConfirmDialog";
import LoadingSpinner from "@/components/ui/LoadingSpinner";
import ErrorMessage from "@/components/ui/ErrorMessage";
import EmptyState from "@/components/ui/EmptyState";

const schema = z.object({
  nome: z.string().min(2, "Nome obrigatório"),
  email: z.string().email("E-mail inválido"),
  telefone: z.string().min(8, "Telefone obrigatório"),
});

type FormData = z.infer<typeof schema>;

export default function ClientesPage() {
  const qc = useQueryClient();
  const [modalOpen, setModalOpen] = useState(false);
  const [editing, setEditing] = useState<Cliente | null>(null);
  const [deleteTarget, setDeleteTarget] = useState<Cliente | null>(null);

  const { data, isLoading, isError } = useQuery({
    queryKey: ["clientes"],
    queryFn: clientesService.listar,
  });

  const {
    register,
    handleSubmit,
    reset,
    formState: { errors },
  } = useForm<FormData>({ resolver: zodResolver(schema) });

  const criar = useMutation({
    mutationFn: (d: ClienteForm) => clientesService.criar(d),
    onSuccess: () => {
      qc.invalidateQueries({ queryKey: ["clientes"] });
      closeModal();
    },
  });

  const atualizar = useMutation({
    mutationFn: ({ id, data }: { id: number; data: ClienteForm }) =>
      clientesService.atualizar(id, data),
    onSuccess: () => {
      qc.invalidateQueries({ queryKey: ["clientes"] });
      closeModal();
    },
  });

  const deletar = useMutation({
    mutationFn: (id: number) => clientesService.deletar(id),
    onSuccess: () => {
      qc.invalidateQueries({ queryKey: ["clientes"] });
      setDeleteTarget(null);
    },
  });

  function openCreate() {
    setEditing(null);
    reset({ nome: "", email: "", telefone: "" });
    setModalOpen(true);
  }

  function openEdit(cliente: Cliente) {
    setEditing(cliente);
    reset({ nome: cliente.nome, email: cliente.email, telefone: cliente.telefone });
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
          {data ? `${data.length} cliente(s) cadastrado(s)` : ""}
        </p>
        <button
          onClick={openCreate}
          className="flex items-center gap-2 rounded-lg bg-indigo-600 px-4 py-2 text-sm font-medium text-white hover:bg-indigo-700"
        >
          <Plus size={16} />
          Novo Cliente
        </button>
      </div>

      <div className="bg-white rounded-xl border border-gray-100 shadow-sm overflow-hidden">
        {isLoading && <LoadingSpinner />}
        {isError && <ErrorMessage />}
        {!isLoading && !isError && data?.length === 0 && <EmptyState message="Nenhum cliente cadastrado." />}
        {!isLoading && !isError && data && data.length > 0 && (
          <div className="overflow-x-auto">
            <table className="w-full text-sm">
              <thead>
                <tr className="bg-gray-50 text-left">
                  <th className="px-6 py-3 font-medium text-gray-500">Nome</th>
                  <th className="px-6 py-3 font-medium text-gray-500">E-mail</th>
                  <th className="px-6 py-3 font-medium text-gray-500">Telefone</th>
                  <th className="px-6 py-3 font-medium text-gray-500 text-right">Ações</th>
                </tr>
              </thead>
              <tbody className="divide-y divide-gray-50">
                {data.map((c: Cliente) => (
                  <tr key={c.id} className="hover:bg-gray-50/50">
                    <td className="px-6 py-3 text-gray-900 font-medium">{c.nome}</td>
                    <td className="px-6 py-3 text-gray-600">{c.email}</td>
                    <td className="px-6 py-3 text-gray-600">{c.telefone}</td>
                    <td className="px-6 py-3 text-right">
                      <div className="flex items-center justify-end gap-2">
                        <button
                          onClick={() => openEdit(c)}
                          className="rounded-lg p-1.5 text-gray-500 hover:bg-gray-100 hover:text-indigo-600"
                        >
                          <Pencil size={15} />
                        </button>
                        <button
                          onClick={() => setDeleteTarget(c)}
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

      <Modal
        open={modalOpen}
        title={editing ? "Editar Cliente" : "Novo Cliente"}
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
            <label className="block text-sm font-medium text-gray-700 mb-1">Telefone</label>
            <input
              {...register("telefone")}
              className="w-full rounded-lg border border-gray-300 px-3 py-2 text-sm focus:outline-none focus:ring-2 focus:ring-indigo-500"
              placeholder="(11) 99999-9999"
            />
            {errors.telefone && (
              <p className="mt-1 text-xs text-red-600">{errors.telefone.message}</p>
            )}
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
        title="Excluir cliente"
        description={`Deseja excluir o cliente "${deleteTarget?.nome}"? Esta ação não pode ser desfeita.`}
        onConfirm={() => deleteTarget && deletar.mutate(deleteTarget.id)}
        onCancel={() => setDeleteTarget(null)}
        loading={deletar.isPending}
      />
    </div>
  );
}
