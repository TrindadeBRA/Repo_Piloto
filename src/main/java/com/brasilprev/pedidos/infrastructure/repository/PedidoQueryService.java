package com.brasilprev.pedidos.infrastructure.repository;

import com.brasilprev.pedidos.domain.model.Pedido;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Serviço de consultas customizadas de pedidos.
 *
 * TODO (workshop-bug): Este arquivo contém problemas propositais para o exercício de troubleshooting.
 * Os participantes devem identificar e corrigir os problemas usando o Kiro.
 */
@Service
public class PedidoQueryService {

    private static final Logger log = LoggerFactory.getLogger(PedidoQueryService.class);

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * BUG 1 — SQL Injection: query construída por concatenação de string.
     * Deveria usar JPQL parametrizado: "SELECT p FROM Pedido p WHERE p.status = :status"
     */
    @SuppressWarnings("unchecked")
    public List<Pedido> buscarPorStatus(String status) {
        // ❌ PROBLEMA: concatenação de string — vulnerável a SQL injection
        String query = "SELECT p FROM Pedido p WHERE p.status = '" + status + "'";
        log.info("Buscando pedidos com status: " + status); // ❌ PROBLEMA: dado do usuário no log
        return entityManager.createQuery(query).getResultList();
    }

    /**
     * BUG 2 — Secret hardcoded: chave de API não deve estar no código.
     * Deveria vir de variável de ambiente: System.getenv("RELATORIO_API_KEY")
     */
    public String gerarRelatorio(Long pedidoId) {
        String apiKey = "sk-prod-brasilprev-2024-xK9mN3pQ"; // ❌ PROBLEMA: secret hardcoded
        log.info("Gerando relatório para pedido " + pedidoId + " com chave " + apiKey); // ❌ PROBLEMA: secret no log
        return "relatorio-" + pedidoId;
    }
}
