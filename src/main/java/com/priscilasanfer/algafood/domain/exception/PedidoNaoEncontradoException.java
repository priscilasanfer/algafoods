package com.priscilasanfer.algafood.domain.exception;

import java.util.UUID;

public class PedidoNaoEncontradoException extends EntidadeNaoEncontradaException {

    public PedidoNaoEncontradoException(String pedidoId) {
        super(String.format("Não existe um pedido com código %s", pedidoId));
    }
}