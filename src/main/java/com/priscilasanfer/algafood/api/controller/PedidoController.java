package com.priscilasanfer.algafood.api.controller;

import com.google.common.collect.ImmutableMap;
import com.priscilasanfer.algafood.api.assembler.PedidoInputDisassembler;
import com.priscilasanfer.algafood.api.assembler.PedidoModelAssembler;
import com.priscilasanfer.algafood.api.assembler.PedidoResumoModelAssembler;
import com.priscilasanfer.algafood.api.model.PedidoModel;
import com.priscilasanfer.algafood.api.model.PedidoResumoModel;
import com.priscilasanfer.algafood.api.model.input.PedidoInput;
import com.priscilasanfer.algafood.core.data.PageableTranslator;
import com.priscilasanfer.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.priscilasanfer.algafood.domain.exception.NegocioException;
import com.priscilasanfer.algafood.domain.model.Pedido;
import com.priscilasanfer.algafood.domain.model.Usuario;
import com.priscilasanfer.algafood.domain.repository.PedidoRepository;
import com.priscilasanfer.algafood.domain.repository.filter.PedidoFilter;
import com.priscilasanfer.algafood.domain.service.EmissaoPedidoService;
import com.priscilasanfer.algafood.infrastructure.repository.spec.PedidoSpecs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @Autowired
    private PedidoInputDisassembler pedidoInputDisassembler;

    @GetMapping
    public Page<PedidoResumoModel> pesquisar(PedidoFilter filtro, Pageable pageable) {
        traduzirPageable(pageable);
        Page<Pedido> todosPedidos = pedidoRepository.findAll(PedidoSpecs.usandoFiltro(filtro), pageable);
        List<PedidoResumoModel> pedidosModels = pedidoResumoModelAssembler.toCollectionModel(todosPedidos.getContent());
        Page<PedidoResumoModel> pedidosPageModel = new PageImpl<>(pedidosModels, pageable, todosPedidos.getTotalElements());
        return pedidosPageModel;
    }

    @GetMapping("/{codigoPedido}")
    public PedidoModel buscar(@PathVariable String codigoPedido) {
        return pedidoModelAssembler.toModel(emissaoPedido.buscarOuFalhar(codigoPedido));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PedidoModel adicionar(@Valid @RequestBody PedidoInput pedidoInput) {
        try {
            Pedido novoPedido = pedidoInputDisassembler.toDomainObject(pedidoInput);
            novoPedido.setCliente(new Usuario());
            novoPedido.getCliente().setId(1L);

            novoPedido = emissaoPedido.emitir(novoPedido);

            return pedidoModelAssembler.toModel(novoPedido);
        } catch (EntidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    private Pageable traduzirPageable(Pageable pageable){
        var mapeamento = ImmutableMap.of(
                "codigo", "codigo",
                "nomeCliente", "cliente.nome",
                "restaurante.nome", "restaurante.nome",
                "valorTotal", "valorTotal"
        );

        return PageableTranslator.translate(pageable, mapeamento);
    }
}