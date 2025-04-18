# 💰 API Bank - Spring Boot REST API

Este projeto é uma aplicação de API REST construída com **Spring Boot**, utilizando práticas modernas de desenvolvimento para simular operações bancárias, como gerenciamento de contas, instituições, usuários e transações.

🔗 Repositório no GitHub: [lucashrt/apiBankwithSpring](https://github.com/lucashrt/apiBankwithSpring)

---

## 🚀 Funcionalidades

- Cadastro e gerenciamento de **Usuários**
- Criação e controle de **Contas bancárias**
- Registro de **Transações**
- Gerenciamento de **Instituições financeiras**
- Configurações e serviços auxiliares para resposta padrão da API

---

## 🛠️ Tecnologias Utilizadas

- Java 24
- Spring Boot 3.4.4
- Spring Web
- Spring Data JPA
- PostgresSQL
- Maven
- Insomnia (Testes)

---

## 📦 Estrutura do Projeto

```
first-spring-app
├── controller/             # Controladores REST
├── domain/                 # Entidades JPA
├── repository/             # Interfaces JPA para persistência
├── service/                # Lógica de negócio
├── configuration/          # Arquivos de configuração
├── resources/              # application.properties, static, etc.
└── FirstSpringAppApplication.java   # Classe principal
```

---

## 📂 Endpoints Principais

> Os endpoints podem ser testados com Postman ou Insomnia após subir a aplicação.

### Usuários
- `GET /users/all` – Lista todos os usuários
- `GET /users/search/{id}` – Lista um usuário específico
- `POST /users/create` – Cria um novo usuário
- `DELETE /users/delete/{id}` - Deleta um usuário

### Contas
- `GET /conta/all` – Lista todas as contas
- `GET /conta/{id}/extrato?instituicaoName=` – Extrato da conta em uma instituição específica
- `GET /conta/{id}/saldo` – Mostra o saldo de uma conta específica
- `POST /conta/create` – Cria uma nova conta

### Instituições
- `GET /instituicoes/all` – Lista instituições
- `POST /instituicoes/create` – Cadastra uma nova instituição

### Transações
- `POST /transacoes/create` – Cria uma nova transação

---

## ⚙️ Como executar

1. Clone o repositório:
   ```bash
   git clone https://github.com/lucashrt/apiBankwithSpring
   cd apiBankwithSpring
   ```

2. Compile o projeto:
   ```bash
   ./mvnw clean install
   ```

3. Execute a aplicação:
   ```bash
   ./mvnw spring-boot:run
   ```

4. Acesse a API em:
   ```
   http://localhost:8080
   ```

---

## 🧪 Testes

Você pode incluir testes com Spring Boot Starter Test, criando arquivos em `src/test/java`.

---

## 👨‍💻 Autor

Desenvolvido por **LucasHRT**.  
Contribuições e sugestões são bem-vindas!

