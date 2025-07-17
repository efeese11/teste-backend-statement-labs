# 📘 Instruções de Execução - API PROSEFA (Selos Fiscais)

Este documento explica como rodar a aplicação localmente e testar os principais endpoints da API.

---

## 🧰 Requisitos

- Java 17+
- Maven
- PostgreSQL (rodando na porta padrão)
- Git

---

## ⚙️ Configuração do banco de dados (PostgreSQL)

Crie um banco chamado `prosefa`:

```sql
CREATE DATABASE prosefa;
# Banco de Dados
spring.datasource.url=jdbc:postgresql://localhost:5432/prosefa
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha

# JPA / Hibernate
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect


▶️ Como rodar a aplicação
Clone o repositório:

bash
Copiar código
git clone https://github.com/Iracelma9/teste-backend-statement-labs.git
cd teste-backend-statement-labs
git checkout desafio-Iracelmapanzo

Compile e rode:

bash
Copiar código
mvn clean install
mvn spring-boot:run
A aplicação estará disponível em:
📍 http://localhost:8080

🔐 Autenticação
🔑 Login
POST /auth/login
Body:

json
Copiar código
{
  "email": "admin@prosefa.com",
  "senha": "1234"
}
Resposta:

json
Copiar código
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6..."
}
Use o token para chamadas autenticadas com:

makefile
Copiar código
Authorization: Bearer SEU_TOKEN_AQUI


📮 Exemplos de chamadas via Postman

✅ Cadastrar empresa
POST /api/empresas

json
Copiar código
{
  "nome": "Empresa Alpha Ltda",
  "nif": "12345678901",
  "tipo": "FABRICANTE"
}

🔍 Buscar empresa por NIF
GET /api/empresas/nif/12345678901

🏷️ Solicitar selo fiscal
POST /api/selos
Header:

makefile
Copiar código
Authorization: Bearer <token>
Body:

json
Copiar código
{
  "empresaId": "uuid_da_empresa",
  "produto": "Notebook Pro X"
}

🔒 Validar selo
PUT /api/selos/PROSEFA-2025-000001/validar
Header: Authorization com token

📄 Listar selos por empresa
GET /api/selos?empresaId=uuid_da_empresa

📋 Listar logs de auditoria
GET /api/logs
Header: Authorization com token

✅ Regras implementadas

Empresas precisam estar ATIVAS para solicitar selo

Empresas com selos não validados há 30+ dias não podem solicitar novos

Selos só podem ser validados uma vez

Todas as ações sensíveis são auditadas


📌 Observações

Toda a API segue boas práticas REST com Spring Boot 3 e Java 17

Autenticação via JWT implementada

Tratamento global de exceções

Estrutura em camadas: Controller, Service, Repository, DTOs...
