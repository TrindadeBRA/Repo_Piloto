package com.brasilprev.pedidos.domain.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String clienteNome;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private BigDecimal valorTotal;

    @Column(nullable = false)
    private LocalDateTime criadoEm;

    public Pedido() {}

    public Pedido(String clienteNome, String status, BigDecimal valorTotal) {
        this.clienteNome = clienteNome;
        this.status = status;
        this.valorTotal = valorTotal;
        this.criadoEm = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public String getClienteNome() { return clienteNome; }
    public String getStatus() { return status; }
    public BigDecimal getValorTotal() { return valorTotal; }
    public LocalDateTime getCriadoEm() { return criadoEm; }

    public void setStatus(String status) { this.status = status; }
}
