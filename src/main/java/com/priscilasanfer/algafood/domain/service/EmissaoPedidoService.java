package com.priscilasanfer.algafood.domain.service;

import com.priscilasanfer.algafood.domain.exception.PedidoNaoEncontradoException;
import com.priscilasanfer.algafood.domain.model.Pedido;
import com.priscilasanfer.algafood.domain.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmissaoPedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public Pedido buscarOuFalhar(Long pedidoId) {
        return pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new PedidoNaoEncontradoException(pedidoId));
    }
}