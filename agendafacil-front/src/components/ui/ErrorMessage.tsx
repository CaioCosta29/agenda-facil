"use client";

import { AlertCircle } from "lucide-react";

interface Props {
  message?: string;
}

export default function ErrorMessage({
  message = "Erro ao carregar os dados. Tente novamente.",
}: Props) {
  return (
    <div className="flex items-center gap-2 rounded-lg bg-red-50 px-4 py-3 text-sm text-red-700">
      <AlertCircle size={16} />
      {message}
    </div>
  );
}
