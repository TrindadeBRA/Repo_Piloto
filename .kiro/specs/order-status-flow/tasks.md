# Plano de Implementação: Fluxo de Status de Pedidos

## Visão Geral

Implementação incremental do fluxo de status nos pedidos, adicionando o enum `OrderStatus`, a lógica de transição no domínio, use cases dedicados, endpoint PATCH para transição e endpoint GET para consulta de transições permitidas. Cada tarefa constrói sobre a anterior, garantindo que o código esteja sempre integrado e funcional.

## Tarefas

- [x] 1. Criar o enum `OrderStatus` e a exceção de domínio
  - [x] 1.1 Criar o enum `OrderStatus` em `domain/model/`
    - Criar `src/main/java/com/brasilprev/orders/domain/model/OrderStatus.java`
    - Definir os valores: PENDENTE, CONFIRMADO, ENVIADO, ENTREGUE
    - Implementar o mapa de transições válidas (`Map<OrderStatus, OrderStatus>`)
    - Implementar `canTransitionTo(OrderStatus target)` retornando boolean
    - Implementar `getNextStatus()` retornando `Optional<OrderStatus>`
    - Implementar `fromString(String value)` com `IllegalArgumentException` para valores inválidos
    - _Requisitos: 1.1, 2.1, 2.2, 2.3, 2.4, 2.5, 2.6_

  - [ ]* 1.2 Escrever teste de propriedade para regra de transição de status
    - **Propriedade 1: Regra de transição de status**
    - Gerar pares aleatórios `(OrderStatus, OrderStatus)` e verificar que `canTransitionTo` retorna `true` sse o destino é o próximo sequencial
    - Adicionar dependência `junit-quickcheck-core` e `junit-quickcheck-generators` ao `pom.xml`
    - **Valida: Requisitos 2.1, 2.2, 2.3, 2.4, 2.5, 2.6**

  - [x] 1.3 Criar `InvalidStatusTransitionException` em `domain/exception/`
    - Criar `src/main/java/com/brasilprev/orders/domain/exception/InvalidStatusTransitionException.java`
    - Estender `RuntimeException` com campos `currentStatus` e `requestedStatus`
    - Mensagem no formato: `"Transição de status inválida: {atual} → {solicitado}"`
    - _Requisitos: 3.1, 3.2_

- [x] 2. Modificar a entidade `Order` para usar o enum `OrderStatus`
  - [x] 2.1 Alterar o campo `status` de `String` para `OrderStatus` na entidade `Order`
    - Modificar `src/main/java/com/brasilprev/orders/domain/model/Order.java`
    - Alterar o tipo do campo `status` de `String` para `OrderStatus`
    - Adicionar `@Enumerated(EnumType.STRING)` no campo
    - Atualizar construtor, getter e setter para usar `OrderStatus`
    - _Requisitos: 1.1, 1.3_

  - [ ]* 2.2 Escrever teste de propriedade para round-trip de persistência
    - **Propriedade 4: Round-trip de persistência do status**
    - Gerar pedidos com status aleatório, salvar e buscar, verificar igualdade do status
    - **Valida: Requisito 1.3**

- [x] 3. Checkpoint — Garantir que a compilação e testes existentes passam
  - Garantir que todos os testes passam, perguntar ao usuário se surgirem dúvidas.

- [x] 4. Implementar os use cases de transição e consulta
  - [x] 4.1 Criar `TransitionOrderStatusUseCase` em `application/usecase/`
    - Criar `src/main/java/com/brasilprev/orders/application/usecase/TransitionOrderStatusUseCase.java`
    - Injetar `OrderRepository`
    - Implementar `execute(Long orderId, OrderStatus newStatus)`: buscar pedido (404 se não encontrado), validar transição via `OrderStatus.canTransitionTo()`, atualizar status e persistir
    - Lançar `InvalidStatusTransitionException` se transição inválida
    - _Requisitos: 2.1, 2.2, 2.3, 2.4, 2.5, 2.6, 3.1, 3.2, 3.3_

  - [ ]* 4.2 Escrever testes unitários para `TransitionOrderStatusUseCase`
    - Testar transição válida (PENDENTE → CONFIRMADO) com mock do repositório
    - Testar transição inválida lançando `InvalidStatusTransitionException`
    - Testar pedido não encontrado lançando exceção 404
    - _Requisitos: 2.1, 3.1, 3.3_

  - [x] 4.3 Criar `GetAllowedTransitionsUseCase` em `application/usecase/`
    - Criar `src/main/java/com/brasilprev/orders/application/usecase/GetAllowedTransitionsUseCase.java`
    - Injetar `OrderRepository`
    - Implementar `execute(Long orderId)`: buscar pedido (404 se não encontrado), retornar lista com próximo status ou lista vazia para ENTREGUE
    - _Requisitos: 6.1, 6.2, 6.3_

  - [ ]* 4.4 Escrever testes unitários para `GetAllowedTransitionsUseCase`
    - Testar retorno para cada status (PENDENTE retorna [CONFIRMADO], ENTREGUE retorna [])
    - Testar pedido não encontrado
    - _Requisitos: 6.1, 6.2, 6.3_

- [x] 5. Modificar `CreateOrderUseCase` para forçar status PENDENTE
  - [x] 5.1 Alterar `CreateOrderUseCase` para ignorar o campo `status` da requisição
    - Modificar `src/main/java/com/brasilprev/orders/application/usecase/CreateOrderUseCase.java`
    - Sempre usar `OrderStatus.PENDENTE` ao criar o pedido, independente do valor enviado na requisição
    - _Requisitos: 1.2, 5.1, 5.2_

  - [ ]* 5.2 Escrever teste de propriedade para status inicial PENDENTE
    - **Propriedade 3: Status inicial sempre PENDENTE**
    - Gerar strings aleatórias para o campo status na criação e verificar que o pedido resultante tem PENDENTE
    - **Valida: Requisitos 1.2, 5.1, 5.2**

- [x] 6. Criar DTO e expor endpoints no controller
  - [x] 6.1 Criar `TransitionStatusRequest` em `interfaces/dto/`
    - Criar `src/main/java/com/brasilprev/orders/interfaces/dto/TransitionStatusRequest.java`
    - Campo `status` com `@NotBlank(message = "Status é obrigatório")`
    - _Requisitos: 4.3, 4.4_

  - [x] 6.2 Adicionar endpoints no `OrderController`
    - Adicionar `PATCH /api/v1/orders/{id}/status` para transição de status
    - Aceitar `@Valid @RequestBody TransitionStatusRequest`, converter status via `OrderStatus.fromString()`, chamar `TransitionOrderStatusUseCase`
    - Retornar HTTP 200 com `ApiResponse.ok(order)`
    - Adicionar `GET /api/v1/orders/{id}/status/transitions` para consulta de transições
    - Chamar `GetAllowedTransitionsUseCase`, retornar HTTP 200 com `ApiResponse.ok(transitions)`
    - _Requisitos: 4.1, 4.2, 4.3, 4.4, 6.1, 6.2, 6.3_

  - [ ]* 6.3 Escrever teste de propriedade para transição válida via endpoint
    - **Propriedade 5: Transição válida via endpoint atualiza o pedido**
    - Gerar pedidos em status não-terminal, aplicar próxima transição via PATCH, verificar HTTP 200 e status atualizado
    - **Valida: Requisito 4.2**

  - [ ]* 6.4 Escrever teste de propriedade para consulta de transições permitidas
    - **Propriedade 6: Consulta de transições permitidas é consistente com as regras**
    - Gerar pedidos em status aleatório, consultar transições via GET, verificar consistência com `canTransitionTo`
    - **Valida: Requisitos 6.1, 6.2**

- [x] 7. Adicionar tratamento de erros no `GlobalExceptionHandler`
  - [x] 7.1 Adicionar handler para `InvalidStatusTransitionException`
    - Modificar `src/main/java/com/brasilprev/orders/interfaces/controller/GlobalExceptionHandler.java`
    - Adicionar `@ExceptionHandler(InvalidStatusTransitionException.class)` retornando HTTP 422 com `ApiResponse.error(ex.getMessage())`
    - Adicionar handler para `IllegalArgumentException` (do `OrderStatus.fromString`) retornando HTTP 400
    - _Requisitos: 3.1, 3.2_

  - [x] 7.2 Escrever teste de propriedade para resposta de erro em transição inválida
    - **Propriedade 2: Resposta de erro para transição inválida**
    - Gerar pares inválidos de status, enviar PATCH, verificar HTTP 422 e mensagem contendo ambos os status
    - **Valida: Requisitos 3.1, 3.2**

- [x] 8. Checkpoint final — Garantir que todos os testes passam
  - Garantir que todos os testes passam, perguntar ao usuário se surgirem dúvidas.

## Notas

- Tarefas marcadas com `*` são opcionais e podem ser puladas para um MVP mais rápido
- Cada tarefa referencia requisitos específicos para rastreabilidade
- Checkpoints garantem validação incremental
- Testes de propriedade validam propriedades universais de corretude
- Testes unitários validam exemplos específicos e edge cases
