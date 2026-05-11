"use client";

import { InboxIcon } from "lucide-react";

interface Props {
  message?: string;
}

export default function EmptyState({ message = "Nenhum registro encontrado." }: Props) {
  return (
    <div className="flex flex-col items-center justify-center py-16 text-gray-400">
      <InboxIcon size={40} strokeWidth={1.5} />
      <p className="mt-3 text-sm">{message}</p>
    </div>
  );
}
