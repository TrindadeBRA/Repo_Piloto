# Documento de Requisitos — Fluxo de Status de Pedidos

## Introdução

Este documento define os requisitos para a implementação de um fluxo de status nos pedidos do sistema de gerenciamento de pedidos (Orders API). O fluxo estabelece uma máquina de estados com transições válidas entre os status: PENDENTE → CONFIRMADO → ENVIADO → ENTREGUE, garantindo que apenas transições permitidas sejam executadas e que transições inválidas sejam rejeitadas com mensagens de erro claras.

## Glossário

- **Sistema_De_Pedidos**: A aplicação Spring Boot responsável pelo gerenciamento de pedidos, incluindo criação, consulta, atualização e exclusão de pedidos.
- **Pedido**: Entidade de domínio que representa um pedido de cliente, contendo identificador, nome do cliente, status, valor total e data de criação.
- **Status_Do_Pedido**: Enumeração que representa o estado atual de um Pedido. Valores válidos: PENDENTE, CONFIRMADO, ENVIADO, ENTREGUE.
- **Transição_De_Status**: Mudança do Status_Do_Pedido de um valor para outro, sujeita a regras de validação.
- **Validador_De_Transição**: Componente de domínio responsável por verificar se uma Transição_De_Status é permitida conforme as regras de negócio.
- **Endpoint_De_Transição**: Endpoint REST dedicado para realizar a Transição_De_Status de um Pedido.

## Requisitos

### Requisito 1: Enumeração de Status do Pedido

**User Story:** Como desenvolvedor, eu quero que os status dos pedidos sejam representados por uma enumeração tipada, para que apenas valores válidos sejam utilizados no sistema.

#### Critérios de Aceitação

1. THE Sistema_De_Pedidos SHALL representar o Status_Do_Pedido como uma enumeração com os valores: PENDENTE, CONFIRMADO, ENVIADO, ENTREGUE.
2. WHEN um Pedido é criado, THE Sistema_De_Pedidos SHALL atribuir o Status_Do_Pedido PENDENTE ao Pedido.
3. THE Sistema_De_Pedidos SHALL persistir o Status_Do_Pedido como uma String na coluna "status" da tabela "orders".

### Requisito 2: Regras de Transição de Status

**User Story:** Como gerente de operações, eu quero que as transições de status sigam um fluxo sequencial definido, para que pedidos não avancem para estados inválidos.

#### Critérios de Aceitação

1. WHILE o Pedido possui Status_Do_Pedido PENDENTE, THE Validador_De_Transição SHALL permitir apenas a Transição_De_Status para CONFIRMADO.
2. WHILE o Pedido possui Status_Do_Pedido CONFIRMADO, THE Validador_De_Transição SHALL permitir apenas a Transição_De_Status para ENVIADO.
3. WHILE o Pedido possui Status_Do_Pedido ENVIADO, THE Validador_De_Transição SHALL permitir apenas a Transição_De_Status para ENTREGUE.
4. WHILE o Pedido possui Status_Do_Pedido ENTREGUE, THE Validador_De_Transição SHALL rejeitar qualquer Transição_De_Status.
5. THE Validador_De_Transição SHALL rejeitar transições que retrocedam no fluxo (ex: CONFIRMADO para PENDENTE).
6. THE Validador_De_Transição SHALL rejeitar transições que pulem etapas no fluxo (ex: PENDENTE para ENVIADO).

### Requisito 3: Tratamento de Transições Inválidas

**User Story:** Como consumidor da API, eu quero receber mensagens de erro claras quando uma transição de status inválida for tentada, para que eu possa entender o motivo da rejeição.

#### Critérios de Aceitação

1. IF uma Transição_De_Status inválida for solicitada, THEN THE Sistema_De_Pedidos SHALL retornar HTTP status 422 (Unprocessable Entity).
2. IF uma Transição_De_Status inválida for solicitada, THEN THE Sistema_De_Pedidos SHALL retornar uma mensagem de erro no formato ApiResponse contendo o status atual do Pedido e o status solicitado.
3. IF o Pedido referenciado não existir, THEN THE Sistema_De_Pedidos SHALL retornar HTTP status 404 (Not Found).

### Requisito 4: Endpoint de Transição de Status

**User Story:** Como consumidor da API, eu quero um endpoint dedicado para alterar o status de um pedido, para que a transição de status seja uma operação explícita e separada da atualização geral do pedido.

#### Critérios de Aceitação

1. THE Endpoint_De_Transição SHALL estar disponível em PATCH /api/v1/orders/{id}/status.
2. WHEN uma requisição PATCH é recebida com um novo status válido, THE Sistema_De_Pedidos SHALL atualizar o Status_Do_Pedido do Pedido e retornar HTTP status 200 com o Pedido atualizado no formato ApiResponse.
3. THE Endpoint_De_Transição SHALL aceitar um corpo de requisição JSON contendo o campo "status" com o novo Status_Do_Pedido desejado.
4. THE Endpoint_De_Transição SHALL validar o campo "status" do corpo da requisição utilizando Bean Validation (@Valid).

### Requisito 5: Integração com Criação de Pedidos

**User Story:** Como consumidor da API, eu quero que pedidos criados iniciem automaticamente com status PENDENTE, para que o fluxo de status seja consistente desde a criação.

#### Critérios de Aceitação

1. WHEN um novo Pedido é criado via POST /api/v1/orders, THE Sistema_De_Pedidos SHALL ignorar o campo "status" da requisição e atribuir PENDENTE como Status_Do_Pedido inicial.
2. THE Sistema_De_Pedidos SHALL retornar o Pedido criado com Status_Do_Pedido PENDENTE na resposta da criação.

### Requisito 6: Consulta de Transições Permitidas

**User Story:** Como consumidor da API, eu quero consultar quais transições de status são permitidas para um pedido, para que a interface possa exibir apenas as ações disponíveis.

#### Critérios de Aceitação

1. WHEN uma requisição GET é recebida em /api/v1/orders/{id}/status/transitions, THE Sistema_De_Pedidos SHALL retornar a lista de Status_Do_Pedido para os quais o Pedido pode transitar.
2. WHILE o Pedido possui Status_Do_Pedido ENTREGUE, THE Sistema_De_Pedidos SHALL retornar uma lista vazia de transições permitidas.
3. IF o Pedido referenciado não existir, THEN THE Sistema_De_Pedidos SHALL retornar HTTP status 404 (Not Found).
