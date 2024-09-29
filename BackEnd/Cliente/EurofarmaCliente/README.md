# Projeto Eurofarma - Backend Cliente

Este repositório contém o projeto backend do cliente Eurofarma para treinamentos, implementado utilizando Spring Boot. A API fornece endpoints para gerenciar quiz e treinamento, incluindo operações com usuários, permissões, departamentos, instrutores, entre outros componentes.

## Índice

- [Documentação da API](#documentação-da-api)
- [Estrutura do Projeto](#estrutura-do-projeto)
- [Configuração e Instalação](#configuração-e-instalação)
- [Dependências](#dependências)
- [Contribuição](#contribuição)
- [Licença](#licença)

## Documentação da API

A documentação da API pode ser acessada localmente e na web:

- **Documentação Local:** [SwaggerDoc Local](https://github.com/IgorKoppen/ProjetoEuroFarma/blob/main/BackEnd/Cliente/EurofarmaCliente/SwaggerDoc/index.html)
- **Documentação Web:** [SwaggerHub](https://app.swaggerhub.com/apis/IGORPASQUALINO/projeto-euro_farma/v1.0.0)

## Estrutura do Projeto

O projeto está organizado nas seguintes pastas e arquivos:

```plaintext
src
├── main
│   ├── java
│   │   └── br
│   │       └── com
│   │           └── connectfy
│   │               └── EurofarmaCliente
│   │                   ├── config
│   │                   │   ├── MessageConfig.java
│   │                   │   ├── OpenApiConfig.java
│   │                   │   ├── SecurityConfig.java
│   │                   │   ├── WebConfig.java
│   │                   │   └── WebConfigProperties.java
│   │                   └── controllers
│   │                       ├── AnswerController.java
│   │                       ├── AuthController.java
│   │                       ├── DepartmentController.java
│   │                       ├── EmployeeController.java
│   │                       ├── InstructorController.java
│   │                       ├── PermissionController.java
│   │                       ├── QuestionController.java
│   │                       ├── QuizController.java
│   │                       ├── RoleController.java
│   │                       ├── TagController.java
│   │                       └── TrainingController.java
│   └── resources
│           ├── application.properties
│           └── static
├── test
└── pom.xml
```

## Configuração e Instalação

### Pré-requisitos

- Java 21
- Maven 3.6.0 ou superior
- Banco de Dados Oracle

### Instalação

1. Clone o repositório para sua máquina local:

    ```bash
    git clone https://github.com/IgorKoppen/ProjetoEuroFarma.git
    cd ProjetoEuroFarma/BackEnd/Cliente/EurofarmaCliente
    ```

2. Configure as propriedades do banco de dados em `src/main/resources/application.properties`;

3. Compile e construa o projeto usando Maven:

    ```bash
    mvn clean install
    ```

4. Inicie a aplicação:

    ```bash
    mvn spring-boot:run
    ```

## Dependências

- **Spring Boot Starter Data JPA**
- **Spring Boot Starter Security**
- **Spring Boot Starter Validation**
- **Spring Boot Starter Web**
- **Spring Security Config**
- **Oracle JDBC Driver**
- **Java JWT (Auth0)**
- **Twilio SDK**
- **Spring Boot Starter HATEOAS**
- **Springdoc OpenAPI Starter WebMVC UI**
- **Jackson Databind**
- **Commons IO**
- **Apache POI**

As dependências completas podem ser encontradas no arquivo `pom.xml` do projeto.

## Licença

Este projeto está licenciado sob a licença MIT. Para mais informações, veja o arquivo `LICENSE`.
