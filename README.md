# Pedidos API — Repo Piloto

> Repositório criado para a apresentação do Kiro para a BrasilPrev.

Projeto de exemplo para demonstrar as funcionalidades do Kiro. API REST de pedidos em Java 17 + Spring Boot 3, com arquitetura Hexagonal.

## Stack

- Java 17
- Spring Boot 3.2
- Spring Data JPA + H2 (banco em memória)
- Bean Validation
- Spring Boot Actuator
- JUnit 5 + Mockito

## Estrutura (Arquitetura Hexagonal)

```
src/main/java/com/brasilprev/pedidos/
├── domain/
│   ├── model/          # Entidades do domínio
│   └── repository/     # Interfaces (portas de saída)
├── application/
│   └── usecase/        # Casos de uso
├── infrastructure/
│   ├── repository/     # Implementações JPA (adaptadores)
│   └── config/         # Configurações e inicialização
└── interfaces/
    ├── controller/     # Controllers REST (adaptadores de entrada)
    └── dto/            # DTOs de request/response
```

## Banco de dados de teste (PostgreSQL)

O projeto usa H2 em memória por padrão, mas há um PostgreSQL disponível via Docker para testes com banco real.

```bash
docker compose -f docker/docker-compose.yml up -d
```

Isso sobe um PostgreSQL na porta `5433` com as seguintes credenciais:

| Parâmetro | Valor |
|-----------|-------|
| Host | `localhost` |
| Porta | `5433` |
| Database | `brasilprev_demo` |
| Usuário | `demo` |
| Senha | `demo123` |

Para derrubar:

```bash
docker compose -f docker/docker-compose.yml down
```

## Como rodar

```bash
./mvnw spring-boot:run
```

## Endpoints

| Método | URL | Descrição |
|--------|-----|-----------|
| GET | `/api/v1/pedidos` | Lista pedidos com paginação |
| GET | `/api/v1/pedidos/{id}` | Busca pedido por ID |
| GET | `/actuator/health` | Health check |

## Como rodar os testes

```bash
./mvnw test
```

## Kiro — Configuração

Este repositório já tem steering e hooks configurados em `.kiro/`:

| Arquivo | Tipo | O que faz |
|---------|------|-----------|
| `.kiro/steering/pr-standards.md` | auto | Padrão de PRs |
| `.kiro/steering/definition-of-done.md` | auto | Definition of Done |
| `.kiro/steering/padroes-java.md` | fileMatch `**/*.java` | Padrões Java/Spring |
| `.kiro/hooks/java-validate-save.kiro.hook` | fileEdited | Valida `.java` ao salvar |
| `.kiro/hooks/java-run-tests.kiro.hook` | agentStop | Roda testes ao terminar |

## Arquivo com bugs (para o workshop)

`PedidoQueryService.java` contém problemas propositais para o exercício de troubleshooting do Dia 2:
- SQL injection por concatenação de string
- Secret hardcoded no código
- Dados sensíveis em logs
