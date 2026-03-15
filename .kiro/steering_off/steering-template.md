---

# Modos de inclusão:
#
# auto → Sempre ativo (padrões gerais)
# fileMatch → Por tipo de arquivo (específico)
# manual → Via # no chat (sob demanda)

inclusion: manual
fileMatchPattern: "**/*.java"
---

# Template de Steering File

<!-- 
Aceita Markdown completo: listas, código, tabelas, links, etc.
O agente lerá tudo e aplicará automaticamente quando gerar código.
-->

## Nomenclatura
- camelCase para variáveis e métodos
- PascalCase para classes  
- UPPER_CASE para constantes

## Estrutura
- Controllers em `interfaces/controller/`
- Use Cases em `application/usecase/`
- Repositories em `domain/repository/`

## Código
- Usar `@Valid` em DTOs de entrada
- Retornar `ApiResponse<T>` em endpoints REST
- Máximo 20 linhas por método

## Segurança
- NUNCA concatenar strings em queries SQL
- Sempre usar PreparedStatement ou JPA

## Exemplo
```java
@RestController
public class PedidoController {
    @GetMapping("/pedidos")
    public ApiResponse<List<Pedido>> listar() {
        return ApiResponse.success(useCase.listar());
    }
}
```