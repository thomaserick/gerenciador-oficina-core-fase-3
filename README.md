# 🛠 API de Gerenciador de Oficina

[![Java](https://img.shields.io/badge/Java-17+-orange?logo=openjdk)](https://openjdk.org/)
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.2.0-green?logo=spring)](https://spring.io/projects/spring-boot)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-14-blue?logo=postgresql)](https://www.postgresql.org/)
[![Docker](https://img.shields.io/badge/Docker-24.0+-blue?logo=docker)](https://www.docker.com/)

API para gerenciamento de uma oficina com autenticação e controle de estoque.

## 📋 Índice

- [Tecnologias](#-tecnologias)
- [Instalação](#-instalação)
- [Autenticação](#-autenticação)
- [Documentação APIs](#-documentação-da-api)
- [Documentação DDD](#-documentação-ddd)

## 🛠 Tecnologias

- **Java 17+** - Linguagem principal
- **Spring Boot 3.3** - Framework backend
- **Spring Security**
- **JWT**
- **JPA/Hibernate**
- **PostgreSQL** - Banco de dados
- **Docker** - Containerização
- **Flyway** - Migrações de banco
- **OpenAPI/Swagger** - Documentação APIs
- **Mockito** - Testes unitários

## ⚙️ Instalação

### Pré-requisitos

- Docker 24.0+
- Docker Compose 2.20+

### Comandos para rodar o projeto local com Docker

1 Suba os containers:

```bash
  docker-compose up 
```

### Comandos para rodar o projeto local

#### Pré-requisitos

- **Java** 17+
- **PostgreSQL** para banco de dados
- **Maven** para gerenciar as dependências do projeto

#### Passos para rodar

1. Clone o repositório

   SSH

    ```
    git@github.com:CaioMC/gerenciador-oficina-core.git
    ```
   Http
    ```
    https://github.com/CaioMC/gerenciador-oficina-core.git
    ```
2. Configure o Banco de Dados
   ```
    psql -U postgres
    CREATE DATABASE gerenciador-oficina;
   ```
3. Configura o profile como `dev`

    ```
    spring.profiles.active=dev
    ```

O sistema rodará na porta `localhost:8081`.

## 🔑 Autenticação

### Endpoints Públicos

- `/usuarios` (POST)
- `/usuarios/login`

Você precisará se autenticar no endpoint `/usuarios/login`, caso não tenha usuário cadastado
utilizar o endpoint `/usuarios`.

```
{
  "nome": "Severino",
  "sobreNome": "da silva",
  "email": "severino@fiap.com.br",
  "senha": "Fiap@1234",
  "ativo": true,
  "perfis": [
    "ADM"
  ]
}

```

## 📚 Documentação da API

### [Swagger](http://localhost:8081/swagger-ui/index.html)

## 📚 Documentação DDD

### [EventStorming](https://miro.com/app/board/uXjVIhTYiq8=/?share_link_id=963111040580)

### [Diagrama](https://drive.google.com/file/d/1gpGtB9AUglij6xUx8oZw5JVPN-rvoWDh/view)



