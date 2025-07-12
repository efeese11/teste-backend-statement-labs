# 🧪 Desafio Técnico - Backend Java
## Programa de Selos Fiscais de Alta Segurança (PROSEFA)

Bem-vindo(a) ao desafio técnico para a vaga de Desenvolvedor(a) Backend no time do STATEMENT LABS. Este teste simula um cenário real da plataforma: a **gestão e validação de Selos Fiscais de Alta Segurança**.

---

## 🎯 Objetivo

Você deverá desenvolver uma **API RESTful** para permitir:
- Registro de empresas autorizadas.
- Solicitação de selos fiscais.
- Emissão e validação de selos.
- Auditoria das ações realizadas.

Este backend deverá seguir boas práticas de desenvolvimento Java, segurança, modelagem de domínio e arquitetura limpa.

---

## 🧱 Tecnologias esperadas

Você pode usar o stack com o qual se sentir mais confortável, **desde que atenda aos requisitos**. No entanto, priorizamos o uso de:

- **Java 17+**
- **Spring Boot**
- JPA/Hibernate
- Banco de dados (PostgreSQL ou H2)
- Maven ou Gradle
- Testes (JUnit, Mockito)
- Autenticação (JWT ou básica)

---

## 🗃️ Entidades principais

### 1. Empresa
- `id`: UUID
- `nome`: String
- `nif`: String (único)
- `tipo`: enum (`FABRICANTE`, `IMPORTADOR`)
- `status`: enum (`ATIVA`, `SUSPENSA`, `BLOQUEADA`)
- `dataRegistro`: LocalDateTime

### 2. SeloFiscal
- `id`: UUID
- `codigo`: String (único, gerado automaticamente, ex: `PROSEFA-2025-000001`)
- `empresa`: referência à Empresa
- `produto`: String
- `dataEmissao`: LocalDateTime
- `estado`: enum (`PENDENTE`, `EMITIDO`, `VALIDADO`, `INVALIDADO`)

### 3. LogAuditoria
- `id`: UUID
- `entidade`: String (ex: `SeloFiscal`, `Empresa`)
- `acao`: String (ex: `VALIDACAO_REALIZADA`, `SOLICITACAO_EMITIDA`)
- `usuario`: String
- `dataHora`: LocalDateTime
- `detalhes`: JSON ou texto

---

## 🔐 Regras de negócio

- Apenas empresas com status `ATIVA` podem solicitar selos.
- O código do selo deve ser **gerado sequencialmente** com prefixo `PROSEFA-<ano>-<sequência>`.
- Um selo só pode ser **validado uma vez**.
- É necessário registrar um **log de auditoria** a cada ação sensível (emissão, validação, bloqueio, etc).
- Opcional: bloquear a solicitação de selos por empresas com selos anteriores **não validados** após 30 dias.

---


---

## ✅ O que será avaliado

- Clareza e organização do código
- Modelagem correta das entidades e regras
- Segurança (ex: validações, autenticação)
- Boas práticas REST
- Testes automatizados
- Qualidade do README e instruções de execução

---

## 🚀 Como submeter

1. Faça um fork ou clone deste repositório.
2. Implemente a sua solução em uma branch chamada `desafio-{seu-nome}`.
3. Crie um arquivo `INSTRUCOES.md` com:
   - Como rodar a aplicação localmente
   - Exemplos de chamadas (via Postman ou cURL)
4. Envie o link do seu repositório (GitHub ou GitLab) para nossa equipe técnica.

---

## 💡 Dicas

- Fique à vontade para usar camadas como `Controller`, `Service`, `Repository`, `DTOs`, etc.
- Se quiser, use Swagger/OpenAPI para documentar os endpoints.
- Use exception handlers globais para retornar erros consistentes.

---

Boa sorte!  
Em caso de dúvidas, entre em contato com o time técnico responsável pelo desafio.

