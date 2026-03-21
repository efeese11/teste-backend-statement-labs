# Desafio Técnico - Backend Java com Spring Boot

Sistema de Gerenciamento de Estacionamento - **STATEMENT LABS**

---

## 1. Descrição do Projeto

Este projeto é um backend para gerenciamento de um estacionamento com as seguintes funcionalidades:

- 50 vagas fixas, criadas automaticamente ao iniciar a aplicação
- Registro de entrada e saída de veículos
- Cálculo de tarifas conforme regras do estacionamento
- Consultas de vagas disponíveis, veículos ativos e histórico de tickets
- Documentação interativa via Swagger UI

O projeto segue **Clean Architecture** e **DDD**, com camadas **Domain**, **Application**, **Infrastructure** e **Presentation**.

---

## 2. Tecnologias Utilizadas

- **Java 21**
- **Spring Boot 3.x**
- **Gradle**
- **PostgreSQL**
- **JPA/Hibernate**
- **Swagger/OpenAPI**

---

## 3. Regras de Negócio

- Estacionamento com **50 vagas fixas**, status **FREE** ou **OCCUPIED**
- Tarifa:
    - Até 6 horas: 300 Kz/h
    - Acima de 6 horas: 200 Kz/h adicional
- Check-out libera automaticamente a vaga
- Tickets ativos são identificados por `exitTime == null`
- Histórico mantém todos os tickets criados

---

## 4. Observações sobre o ambiente

> Devido a limitações e problemas no computador, **o Docker Compose com PostgreSQL atrasou a envio do projeto pk precisava testar antes de subir**.

## 5. Endpoints da API

> **Nota:** autenticação e tratamento de erros não foram implementados neste projeto. Todos os endpoints retornam **JSON**.

### 5.1 Consultar vagas disponíveis
- **GET** `/api/parking-spots/free`
- **Descrição:** Retorna todas as vagas que estão livres no estacionamento.
- **Exemplo de resposta:**
```json
[
  {"id": 1, "code": "A1", "status": "FREE"},
  {"id": 2, "code": "A2", "status": "FREE"}
]