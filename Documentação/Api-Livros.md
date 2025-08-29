# 📖 Documentação do Projeto Api-Livros

Este documento descreve em detalhes a arquitetura, funcionalidades e endpoints da aplicação **Api-Livros**.

---

## 📌 Visão Geral

O **Api-Livros** é uma aplicação desenvolvida em **Java + Spring Boot**, com banco de dados **H2 em memória**, que disponibiliza uma API REST para gerenciamento de livros.  

Funcionalidades principais:
- Listar todos os livros cadastrados.
- Buscar livros por ID.
- Inicialização automática com **seeding** de dados (4 livros).

---

## 🏗️ Arquitetura

A aplicação segue a arquitetura em camadas do **Spring Boot**:

- **Entity** → Representa as tabelas do banco (classe `Book`).
- **Repository** → Interface que acessa o banco de dados (`BookRepository`).
- **Controller** → Define os endpoints da API (`BookController`).
- **Application (ApiLivrosApplication.java)** → Classe principal que inicializa o projeto.

Fluxo resumido:

---

## 📂 Estrutura de Diretórios
Api-Livros/
├── .gitignore
├── .mvn/
│ └── wrapper/
├── mvnw
├── mvnw.cmd
├── pom.xml
└── src/
└── main/
└── java/
└── com/
└── apilivros/
├── ApiLivrosApplication.java
├── entity/
│ └── Book.java
├── repository/
│ └── BookRepository.java
└── controller/
└── BookController.java

- **`entity/`** → Contém as classes que representam as entidades do banco.
- **`repository/`** → Interface para acessar os dados do banco.
- **`controller/`** → Define os endpoints da API.
- **`ApiLivrosApplication.java`** → Classe principal que sobe a aplicação.

