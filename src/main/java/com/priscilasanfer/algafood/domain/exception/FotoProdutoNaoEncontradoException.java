package com.priscilasanfer.algafood.domain.exception;

public class FotoProdutoNaoEncontradoException extends EntidadeNaoEncontradaException {
    public FotoProdutoNaoEncontradoException(String message) {
        super(message);
    }

    public FotoProdutoNaoEncontradoException(Long produtoId, Long restauranteId) {
        this(String.format("Não existe um cadastro de foto do produto com código %d para o restaurante de código %d",
                produtoId, restauranteId));
    }
}
