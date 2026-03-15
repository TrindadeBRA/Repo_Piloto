# Orders API - Swagger Documentation

## 📋 O que tem na pasta "01 - Pilar 1"

Esta pasta contém:

### **Arquivos de Código:**
- `with-steering/` - Versão com steering (guias de desenvolvimento)
- `without-steering/` - Versão sem steering
- Ambas contêm os mesmos arquivos Java traduzidos para inglês

### **Documentação API:**
- `orders-api-swagger.yaml` - Especificação OpenAPI 3.0 completa
- `README-SWAGGER.md` - Este arquivo com instruções

## 🚀 Como Acessar a Documentação Swagger

### **1. Inicie a Aplicação:**
```bash
cd Repo_Piloto
mvn spring-boot:run
```

### **2. Acesse as URLs do Swagger:**

#### **Interface Visual (Swagger UI):**
```
http://localhost:8081/swagger-ui/index.html
```
- Interface interativa para testar os endpoints
- Permite executar requisições diretamente no browser
- Visualização amigável da documentação

#### **JSON da API:**
```
http://localhost:8081/api-docs
```
- Especificação OpenAPI em formato JSON
- Para integração com ferramentas de desenvolvimento

#### **YAML da API:**
```
http://localhost:8081/api-docs.yaml
```
- Especificação OpenAPI em formato YAML
- Mais legível para humanos

## 📖 Funcionalidades Documentadas

### **Endpoints Disponíveis:**

#### **1. Listar Todos os Pedidos**
- **URL:** `GET /api/v1/orders`
- **Descrição:** Retorna lista de todos os pedidos
- **Resposta:** Array de pedidos ou lista vazia

#### **2. Buscar Pedido por ID**
- **URL:** `GET /api/v1/orders/{id}`
- **Descrição:** Busca pedido específico pelo ID
- **Parâmetros:** `id` (Long) - ID do pedido
- **Respostas:**
  - `200` - Pedido encontrado
  - `404` - Pedido não encontrado
  - `500` - Erro interno

### **Modelos de Dados:**

#### **Order (Pedido):**
```json
{
  "id": 1,
  "customerName": "John Silva",
  "status": "PENDING",
  "totalAmount": 150.00,
  "createdAt": "2026-03-15T03:01:17"
}
```

#### **ApiResponse (Resposta Padrão):**
```json
{
  "success": true,
  "data": { /* dados do pedido ou array */ },
  "message": null
}
```

## 🛠️ Como Testar no Swagger UI

### **1. Acesse:** `http://localhost:8081/swagger-ui/index.html`

### **2. Teste "List Orders":**
- Clique em `GET /api/v1/orders`
- Clique em "Try it out"
- Clique em "Execute"
- Verá resposta: `{"success":true,"data":[],"message":null}`

### **3. Teste "Find Order by ID":**
- Clique em `GET /api/v1/orders/{id}`
- Clique em "Try it out"
- Digite `1` no campo `id`
- Clique em "Execute"
- Verá resposta: `{"success":false,"data":null,"message":"Order not found: 1"}`

## 📁 Arquivo YAML Standalone

O arquivo `orders-api-swagger.yaml` é uma especificação completa que pode ser:

### **Importada em ferramentas como:**
- Postman (Import > OpenAPI)
- Insomnia (Import > OpenAPI)
- Swagger Editor (https://editor.swagger.io/)
- API Gateway tools

### **Usada para:**
- Gerar clientes SDK em várias linguagens
- Validar contratos de API
- Documentação offline
- Integração com CI/CD

## 🎯 Benefícios da Documentação Swagger

### **Para Desenvolvedores:**
- ✅ Documentação sempre atualizada
- ✅ Testes interativos
- ✅ Exemplos de requisições/respostas
- ✅ Validação de contratos

### **Para QA/Testes:**
- ✅ Interface para testes manuais
- ✅ Exemplos de dados válidos
- ✅ Códigos de resposta documentados

### **Para Integração:**
- ✅ Especificação padronizada (OpenAPI)
- ✅ Geração automática de clientes
- ✅ Contratos bem definidos

## 🔧 Configurações Aplicadas

### **Dependência Maven:**
```xml
<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
    <version>2.2.0</version>
</dependency>
```

### **Configuração application.yml:**
```yaml
springdoc:
  api-docs:
    path: /api-docs
  swagger-ui:
    path: /swagger-ui/index.html
    operationsSorter: method
    tagsSorter: alpha
    tryItOutEnabled: true
    filter: true
```

### **Anotações Swagger Aplicadas:**
- `@Tag` - Agrupamento de endpoints
- `@Operation` - Descrição de operações
- `@ApiResponses` - Códigos de resposta
- `@Parameter` - Parâmetros de entrada
- `@Schema` - Modelos de dados

A documentação está completa e pronta para uso! 🚀