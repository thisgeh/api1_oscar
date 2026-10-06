# 🏆 API Oscar

Uma API RESTful desenvolvida em Spring Boot para gerenciamento de premiações e categorias do Oscar. Este projeto foi desenvolvido como parte das atividades da FIAP.

## 🛠️ Tecnologias Utilizadas

*   **Java 17+**
*   **Spring Boot** (Web, Data JPA)
*   **SQL Server** (Banco de Dados)
*   **Docker** (Infraestrutura)
*   **Lombok** (Produtividade)
*   **Springdoc OpenAPI / Swagger**

---

## ⚙️ Profiles

A aplicação possui dois profiles de execução, controlados pela variável de ambiente `SPRING_PROFILES_ACTIVE`:

| Profile | Arquivo | Comportamento |
|---|---|---|
| `default` | `application.properties` | Uso local/dev. Cria o schema automaticamente e atualiza as tabelas (`ddl-auto=update`). |
| `prd` | `application-prd.properties` | Uso em produção. **Não** cria banco nem tabelas automaticamente (`ddl-auto=none`). O schema e as tabelas devem existir previamente. |

---

## 🔐 Variáveis de ambiente

| Variável | Descrição | Exemplo |
|---|---|---|
| `DB_SERVER_URL` | Endereço do servidor SQL Server | `localhost` |
| `DB_SERVER_PORT` | Porta do SQL Server | `1433` |
| `DB_SCHEMA` | Nome do banco de dados | `db_oscar` |
| `DB_USER` | Usuário do banco | `sa` |
| `DB_PWD` | Senha do banco | `1q2w3e4R@` |
| `SPRING_PROFILES_ACTIVE` | Profile ativo do Spring Boot | `default` ou `prd` |

> No profile `default` todas as variáveis têm valor padrão (funciona sem configurar nada). No profile `prd` **todas são obrigatórias**.

---

## 🐳 Infraestrutura: Como subir o Banco de Dados

Para que a API funcione corretamente, é necessário ter um banco de dados SQL Server rodando. Para facilitar os testes e a avaliação, utilizamos o **Docker**.

Siga os passos abaixo para subir o banco de dados do zero:

1. Certifique-se de que o [Docker Desktop](https://www.docker.com/products/docker-desktop/) está instalado e rodando na sua máquina.
2. Abra o seu terminal e execute o seguinte comando:

```bash
docker run -d \
    --name sqlserver \
    --rm \
    -e MSSQL_SA_PASSWORD=1q2w3e4R@ \
    -e "ACCEPT_EULA=Y" \
    -p 1433:1433 \
    mcr.microsoft.com/mssql/server:latest
```

3. Crie o banco de dados:

```bash
docker exec sqlserver /opt/mssql-tools/bin/sqlcmd -S localhost -U sa -P 1q2w3e4R@ -Q "CREATE DATABASE db_oscar"
```

---

## 📥 Executando a partir da imagem publicada no Docker Hub

### 1. Baixar a imagem

```bash
docker pull thisgeh/api1_oscar:1.0
```

### 2. Executar o container

**Profile `default`** (com valores padrão, ideal para teste rápido):

```bash
docker run \
  -p 8080:8080 \
  -e SPRING_PROFILES_ACTIVE=default \
  -e DB_SERVER_URL=host.docker.internal \
  -e DB_SERVER_PORT=1433 \
  -e DB_SCHEMA=db_oscar \
  -e DB_USER=sa \
  -e DB_PWD=1q2w3e4R@ \
  thisgeh/api1_oscar:1.0
```

**Profile `prd`** (produção — banco e tabelas já devem existir):

```bash
docker run \
  -p 8080:8080 \
  -e SPRING_PROFILES_ACTIVE=prd \
  -e DB_SERVER_URL=host.docker.internal \
  -e DB_SERVER_PORT=1433 \
  -e DB_SCHEMA=db_oscar \
  -e DB_USER=sa \
  -e DB_PWD=1q2w3e4R@ \
  thisgeh/api1_oscar:1.0
```

> **Nota:** `host.docker.internal` permite que o container acesse serviços rodando na máquina host. No Linux, dependendo da configuração do Docker, pode ser necessário `--add-host=host.docker.internal:host-gateway`.

A aplicação ficará disponível e documentada em: http://localhost:8080

---

## 💻 Executando localmente (sem Docker)

### Pré-requisitos

Java 17, Maven (ou use o Maven Wrapper), SQL Server rodando.

### 1. Configurar variáveis de ambiente

**Linux / macOS**

```sh
export DB_SERVER_URL=localhost
export DB_SERVER_PORT=1433
export DB_SCHEMA=db_oscar
export DB_USER=sa
export DB_PWD=1q2w3e4R@
export SPRING_PROFILES_ACTIVE=default
```

**Windows PowerShell**

```powershell
$env:DB_SERVER_URL="localhost"
$env:DB_SERVER_PORT="1433"
$env:DB_SCHEMA="db_oscar"
$env:DB_USER="sa"
$env:DB_PWD="1q2w3e4R@"
$env:SPRING_PROFILES_ACTIVE="default"
```

### 2. Rodar a aplicação

```sh
./mvnw spring-boot:run
```

No Windows:

```powershell
.\mvnw.cmd spring-boot:run
```

A aplicação será iniciada em `http://localhost:8080`.

---

## 📦 Build e publicação da imagem Docker

### 1. Build da imagem

Na raiz do projeto (onde está o `Dockerfile`):

```bash
docker build -t thisgeh/api1_oscar:1.0 .
```

### 2. Testar localmente

```bash
docker run -p 8080:8080 -e SPRING_PROFILES_ACTIVE=default thisgeh/api1_oscar:1.0
```

### 3. Login e push no Docker Hub

```bash
docker login
docker push thisgeh/api1_oscar:1.0
```