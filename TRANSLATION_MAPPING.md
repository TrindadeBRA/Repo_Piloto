# Translation Mapping - Portuguese to English

## Folder Structure Translation

### Original Portuguese Structure:
- `sem-steering/` → `without-steering/`
- `com-steering/` → `with-steering/`
- `src/main/java/com/brasilprev/pedidos/` → `src-english/main/java/com/brasilprev/orders/`

### File Name Translations:
- `PedidoController.java` → `OrderController.java`
- `PedidoRepository.java` → `OrderRepository.java`
- `PedidoRepositoryImpl.java` → `OrderRepositoryImpl.java`
- `PedidoJpaRepository.java` → `OrderJpaRepository.java`
- `ListarPedidosUseCase.java` → `ListOrdersUseCase.java`
- `BuscarPedidoUseCase.java` → `FindOrderUseCase.java`
- `ListarPedidosUseCaseTest.java` → `ListOrdersUseCaseTest.java`
- `BuscarPedidoUseCaseTest.java` → `FindOrderUseCaseTest.java`
- `Pedido.java` → `Order.java`
- `PedidosApplication.java` → `OrdersApplication.java`

## Class and Variable Name Translations:

### Domain Model (Pedido → Order):
- `clienteNome` → `customerName`
- `valorTotal` → `totalAmount`
- `criadoEm` → `createdAt`

### Method Names:
- `executar()` → `execute()`
- `listar()` → `list()`
- `buscar()` → `find()`

### Package Names:
- `com.brasilprev.pedidos` → `com.brasilprev.orders`

### Test Method Names:
- `deve_retornarListaDePedidos_quando_repositorioTemDados()` → `should_returnListOfOrders_when_repositoryHasData()`
- `deve_retornarListaVazia_quando_repositorioNaoTemDados()` → `should_returnEmptyList_when_repositoryHasNoData()`
- `deve_retornarPedido_quando_idExiste()` → `should_returnOrder_when_idExists()`
- `deve_lancarExcecao_quando_pedidoNaoEncontrado()` → `should_throwException_when_orderNotFound()`

### Comments and Messages:
- `"Pedido não encontrado"` → `"Order not found"`
- `"Porta de saída — interface do domínio para persistência"` → `"Output port — domain interface for persistence"`
- `"Envelope padrão para todas as respostas da API"` → `"Standard envelope for all API responses"`

### API Endpoints:
- `/pedidos` → `/orders`
- `/api/v1/pedidos` → `/api/v1/orders`

### Database Table:
- `@Table(name = "pedidos")` → `@Table(name = "orders")`

## Status Values (kept in English as they were already in English):
- `"PENDENTE"` → `"PENDING"`
- `"APROVADO"` → `"APPROVED"`

## Test Data Names:
- `"João Silva"` → `"John Silva"`
- `"Maria Souza"` → `"Mary Souza"`