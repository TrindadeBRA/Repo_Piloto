# Demo MCP — PostgreSQL

## 1. Subir o banco

```bash
cd docker
docker compose up -d
```

## 2. Configurar o MCP no Kiro

Adicione em `~/.kiro/settings/mcp.json`:

```json
{
  "mcpServers": {
    "postgres-demo": {
      "command": "uvx",
      "args": [
        "mcp-server-postgres",
        "postgresql://demo:demo123@localhost:5433/brasilprev_demo"
      ],
      "disabled": false,
      "autoApprove": []
    }
  }
}
```

## 3. Prompts para a demo

Após conectar, use estes prompts no chat do Kiro:

**Exploração:**
- "Quais tabelas existem no banco?"
- "Descreva a estrutura da tabela clientes"

**Consultas:**
- "Quantos clientes ativos temos por estado?"
- "Quais clientes têm plano PREMIUM e estão inativos?"
- "Liste os 5 estados com mais clientes"

**Análise:**
- "Qual a distribuição de clientes por plano?"
- "Crie um relatório resumido dos clientes por região"

**Geração de código:**
- "Com base na estrutura da tabela clientes, gere o entity JPA e o repository Spring Data"
- "Gere um endpoint REST para buscar clientes por estado e plano"
