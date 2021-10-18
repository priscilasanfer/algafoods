package com.priscilasanfer.algafood.api.controller;

import com.priscilasanfer.algafood.api.assembler.PedidoModelAssembler;
import com.priscilasanfer.algafood.api.assembler.PedidoResumoModelAssembler;
import com.priscilasanfer.algafood.api.model.PedidoModel;
import com.priscilasanfer.algafood.api.model.PedidoResumoModel;
import com.priscilasanfer.algafood.domain.repository.PedidoRepository;
import com.priscilasanfer.algafood.domain.service.EmissaoPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoController {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private EmissaoPedidoService emissaoPedido;

    @Autowired
    private PedidoModelAssembler pedidoModelAssembler;

    @Autowired
    private PedidoResumoModelAssembler pedidoResumoModelAssembler;

    @GetMapping
    public List<PedidoResumoModel> listar() {
         return pedidoResumoModelAssembler.toCollectionModel(pedidoRepository.findAll());
    }

    @GetMapping("/{pedidoId}")
    public PedidoModel buscar(@PathVariable Long pedidoId) {
        return pedidoModelAssembler.toModel(emissaoPedido.buscarOuFalhar(pedidoId));
    }
}