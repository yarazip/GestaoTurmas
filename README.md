# 📚 Gestão de Turmas

Este projeto consiste em uma aplicação completa para a **Gestão de Turmas**, desenvolvido como trabalho prático ao longo do semestre. A aplicação contempla funcionalidades para gerenciar as entidades **Aluno**, **Professor** e **Turma**, com suporte completo a operações de CRUD (criar, listar, editar e remover).

## 🛠️ Tecnologias Utilizadas

- Linguagem: Java 
- Framework: Spring Boot
- Banco de Dados: PostgreSQL 
- Front-end: HTML/CSS/JavaScript /

## 📁 Estrutura do Projeto

- `/src` – Código-fonte da aplicação
- `/resources` – Configurações, arquivos estáticos e templates
- `/sql` – Scripts de criação do banco de dados
- `/docs` – Documentação e materiais de apoio (ex: vídeo explicativo)

## ⚙️ Funcionalidades Implementadas

### 👨‍🎓 Aluno
- Cadastro de alunos
- Listagem de todos os alunos
- Edição de dados do aluno
- Remoção de aluno

### 👩‍🏫 Professor
- Cadastro de professores
- Listagem de todos os professores
- Edição de dados do professor
- Remoção de professor

### 🏫 Turma
- Cadastro de turmas
- Associação de alunos e professores às turmas
- Listagem de turmas com alunos e professores vinculados
- Edição e exclusão de turmas

## 🔄 Relacionamentos entre Entidades

- Um **professor** pode ministrar várias **turmas**
- Um **aluno** pode participar de várias **turmas**
- Uma **turma** pode conter vários **alunos** e **um professor**

## 🧪 Como Executar o Projeto

### 1. Clone o repositório

```bash
git clone https://github.com/yarazip/GestaoTurmas.git
