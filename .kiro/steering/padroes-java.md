---
inclusion: fileMatch
fileMatchPattern: '**/*.java'
---

# Padrões Java — Backend

## Nomenclatura
- Classes: PascalCase (ex: `OrderService`, `PedidoController`)
- Métodos e variáveis: camelCase (ex: `findOrderById`, `totalValue`)
- Constantes: UPPER_SNAKE_CASE (ex: `MAX_RETRY_COUNT`)
- Pacotes: lowercase (ex: `com.brasilprev.orders.domain`)

## Limites de Código
- Métodos: máximo 30 linhas
- Classes: máximo 300 linhas
- Parâmetros por método: máximo 4
- Complexidade ciclomática: máximo 10
- Aninhamento: máximo 3 níveis

## Arquitetura Hexagonal
- `domain/` — entidades, value objects, interfaces de repositório (sem dependência de infra)
- `application/` — use cases, serviços de aplicação
- `infrastructure/` — implementações de repositório, clients externos, configs
- `interfaces/` — controllers REST, DTOs de entrada/saída
- Regra: domain NÃO depende de infrastructure

## API REST
- Versionamento: `/api/v1/`
- Respostas padronizadas com `ApiResponse<T>`
- HTTP status corretos: 200, 201, 400, 404, 422, 500

## Segurança
- Queries JPQL parametrizadas com `:param` — NUNCA concatenar strings
- Secrets via `application.yml` com variáveis de ambiente — NUNCA hardcoded
- Sem dados sensíveis em logs (CPF, senha, token)

## Validação
- `@Valid` obrigatório nos controllers para DTOs de entrada
- `@NotNull`, `@Size`, `@Email` nos campos dos DTOs
- Tratamento de erros com `@ControllerAdvice`

## Testes
- JUnit 5 + Mockito para unitários
- Nome: `deve_[resultadoEsperado]_quando_[condicao]`
- Cobertura obrigatória: happy path + edge cases + erros esperados

## Observabilidade
- Logs com SLF4J + formato JSON estruturado
- `correlationId` via MDC em todas as requisições
- `/actuator/health` obrigatório em toda API
