# AgendaFácil

Sistema de agendamentos que permite o gerenciamento de clientes, profissionais e agendamentos, com notificação automática por email ao confirmar um agendamento.

---

## Sobre o projeto

O AgendaFácil é composto por dois serviços independentes que se comunicam de forma assíncrona via RabbitMQ:

- **agendafacil-api** — responsável pelo CRUD de clientes, profissionais e agendamentos, além das regras de negócio como conflito de horário e transição de status.
- **agendafacil-notificacao** — responsável por consumir os eventos de agendamento confirmado e enviar emails para o cliente e o profissional envolvidos.

Quando um agendamento é confirmado, a API publica um evento no RabbitMQ. O serviço de notificação consome esse evento e envia os emails de forma assíncrona, sem bloquear a resposta ao usuário.

---

## Arquitetura

```
agendafacil-api
  └── confirma agendamento
  └── publica evento → FanoutExchange (agendamento.ex)
                            ├── agendamento.cliente.queue → notifica cliente por email
                            └── agendamento.profissional.queue → notifica profissional por email

Falha no Consumer → retry (4 tentativas) → Dead Letter Queue (DLQ)
```

Ambos os serviços seguem a **Arquitetura Hexagonal**, com separação clara entre domínio, casos de uso, portas e adaptadores.

---

## Tecnologias

**Backend**
- Java 21
- Spring Boot 3.5
- Spring AMQP (RabbitMQ)
- Spring Data JPA
- Spring Mail
- MapStruct
- MySQL

**Mensageria**
- RabbitMQ com Fanout Exchange
- Dead Letter Queue (DLQ) e Dead Letter Exchange (DLX)
- Retry automático com backoff configurável

**Infraestrutura**
- Docker e Docker Compose
- Dockerfile por serviço

**Frontend**
- Next.js
- TypeScript
- TailwindCSS

---

## Funcionalidades

- Cadastro, listagem, atualização e remoção de clientes e profissionais
- Criação de agendamentos com validação de conflito de horário
- Transição de status com regras de negócio (PENDENTE → CONFIRMADO → CONCLUÍDO / CANCELADO)
- Notificação por email ao cliente e ao profissional quando um agendamento é confirmado
- Tratamento de falhas com retry e Dead Letter Queue