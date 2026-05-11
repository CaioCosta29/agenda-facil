"use client";

import { usePathname } from "next/navigation";

const titles: Record<string, string> = {
  "/": "Dashboard",
  "/clientes": "Clientes",
  "/profissionais": "Profissionais",
  "/agendamentos": "Agendamentos",
};

export default function Header() {
  const pathname = usePathname();
  const title = titles[pathname] ?? "AgendaFácil";

  return (
    <header className="h-16 flex items-center border-b border-gray-100 bg-white px-6">
      <h1 className="text-lg font-semibold text-gray-900">{title}</h1>
    </header>
  );
}
