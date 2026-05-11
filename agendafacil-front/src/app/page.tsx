"use client";

import { useQuery } from "@tanstack/react-query";
import { Users, Briefcase, CalendarCheck, Clock } from "lucide-react";
import MetricCard from "@/components/ui/MetricCard";
import Badge from "@/components/ui/Badge";
import LoadingSpinner from "@/components/ui/LoadingSpinner";
import ErrorMessage from "@/components/ui/ErrorMessage";
import { clientesService } from "@/services/clientes";
import { profissionaisService } from "@/services/profissionais";
import { agendamentosService } from "@/services/agendamentos";
import type { Agendamento } from "@/types";

function formatDateTime(dataHora: string) {
  return new Date(dataHora).toLocaleString("pt-BR", {
    day: "2-digit",
    month: "2-digit",
    year: "numeric",
    hour: "2-digit",
    minute: "2-digit",
  });
}

function isToday(dataHora: string) {
  const d = new Date(dataHora);
  const today = new Date();
  return (
    d.getDate() === today.getDate() &&
    d.getMonth() === today.getMonth() &&
    d.getFullYear() === today.getFullYear()
  );
}

export default function DashboardPage() {
  const clientes = useQuery({ queryKey: ["clientes"], queryFn: clientesService.listar });
  const profissionais = useQuery({
    queryKey: ["profissionais"],
    queryFn: profissionaisService.listar,
  });
  const agendamentos = useQuery({
    queryKey: ["agendamentos"],
    queryFn: agendamentosService.listar,
  });

  const isLoading = clientes.isLoading || profissionais.isLoading || agendamentos.isLoading;
  const isError = clientes.isError || profissionais.isError || agendamentos.isError;

  const agendamentosHoje = (agendamentos.data ?? []).filter((a: Agendamento) =>
    isToday(a.dataHora)
  );

  const proximos = (agendamentos.data ?? [])
    .filter((a: Agendamento) => new Date(a.dataHora) >= new Date())
    .sort(
      (a: Agendamento, b: Agendamento) =>
        new Date(a.dataHora).getTime() - new Date(b.dataHora).getTime()
    )
    .slice(0, 8);

  if (isLoading) return <LoadingSpinner />;
  if (isError) return <ErrorMessage />;

  return (
    <div className="space-y-6">
      <div className="grid grid-cols-1 sm:grid-cols-2 xl:grid-cols-4 gap-4">
        <MetricCard
          title="Clientes"
          value={clientes.data?.length ?? 0}
          icon={Users}
          color="bg-indigo-500"
        />
        <MetricCard
          title="Profissionais"
          value={profissionais.data?.length ?? 0}
          icon={Briefcase}
          color="bg-violet-500"
        />
        <MetricCard
          title="Agendamentos hoje"
          value={agendamentosHoje.length}
          icon={CalendarCheck}
          color="bg-emerald-500"
        />
        <MetricCard
          title="Total de agendamentos"
          value={agendamentos.data?.length ?? 0}
          icon={Clock}
          color="bg-amber-500"
        />
      </div>

      <div className="bg-white rounded-xl border border-gray-100 shadow-sm">
        <div className="px-6 py-4 border-b border-gray-100">
          <h2 className="font-semibold text-gray-900">Próximos Agendamentos</h2>
        </div>
        {proximos.length === 0 ? (
          <p className="px-6 py-10 text-sm text-center text-gray-400">
            Nenhum agendamento futuro.
          </p>
        ) : (
          <div className="overflow-x-auto">
            <table className="w-full text-sm">
              <thead>
                <tr className="bg-gray-50 text-left">
                  <th className="px-6 py-3 font-medium text-gray-500">Cliente</th>
                  <th className="px-6 py-3 font-medium text-gray-500">Profissional</th>
                  <th className="px-6 py-3 font-medium text-gray-500">Data / Hora</th>
                  <th className="px-6 py-3 font-medium text-gray-500">Status</th>
                </tr>
              </thead>
              <tbody className="divide-y divide-gray-50">
                {proximos.map((a: Agendamento) => (
                  <tr key={a.id} className="hover:bg-gray-50/50">
                    <td className="px-6 py-3 text-gray-900">{a.nomeCliente}</td>
                    <td className="px-6 py-3 text-gray-700">{a.nomeProfissional}</td>
                    <td className="px-6 py-3 text-gray-600">{formatDateTime(a.dataHora)}</td>
                    <td className="px-6 py-3">
                      <Badge status={a.status} />
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        )}
      </div>
    </div>
  );
}
