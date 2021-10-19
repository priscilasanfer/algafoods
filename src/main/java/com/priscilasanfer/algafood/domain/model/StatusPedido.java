package com.priscilasanfer.algafood.domain.model;

import java.util.Arrays;
import java.util.List;

public enum StatusPedido {
    CRIADO("Criado"),
    CONFIRMADO("Confirmado", CRIADO),
    ENTREGUE("Entregue", CONFIRMADO),
    CANCELADO("Cancelado", CRIADO);

    private String descricao;
    private List<StatusPedido> statusAnterior;

    StatusPedido(String descricao, StatusPedido... statusAnteriores) {
        this.descricao = descricao;
        this.statusAnterior = Arrays.asList(statusAnteriores);
    }

    public String getDescricao() {
        return this.descricao;
    }

    public boolean naoPodeAlterarPara(StatusPedido novoStatus){
        return !novoStatus.statusAnterior.contains(this);
    }
}
