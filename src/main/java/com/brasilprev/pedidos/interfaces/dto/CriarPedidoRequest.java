package com.brasilprev.pedidos.interfaces.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public class CriarPedidoRequest {

    @NotBlank(message = "Nome do cliente é obrigatório")
    private String clienteNome;

    @NotNull(message = "Valor total é obrigatório")
    @Positive(message = "Valor total deve ser positivo")
    private BigDecimal valorTotal;

    public String getClienteNome() { return clienteNome; }
    public BigDecimal getValorTotal() { return valorTotal; }

    public void setClienteNome(String clienteNome) { this.clienteNome = clienteNome; }
    public void setValorTotal(BigDecimal valorTotal) { this.valorTotal = valorTotal; }
}
