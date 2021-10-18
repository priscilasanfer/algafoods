package com.priscilasanfer.algafood.api.controller;

import com.priscilasanfer.algafood.api.assembler.PedidoInputDisassembler;
import com.priscilasanfer.algafood.api.assembler.PedidoModelAssembler;
import com.priscilasanfer.algafood.api.assembler.PedidoResumoModelAssembler;
import com.priscilasanfer.algafood.api.model.PedidoModel;
import com.priscilasanfer.algafood.api.model.PedidoResumoModel;
import com.priscilasanfer.algafood.api.model.input.PedidoInput;
import com.priscilasanfer.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.priscilasanfer.algafood.domain.exception.NegocioException;
import com.priscilasanfer.algafood.domain.model.Pedido;
import com.priscilasanfer.algafood.domain.model.Usuario;
import com.priscilasanfer.algafood.domain.repository.PedidoRepository;
import com.priscilasanfer.algafood.domain.service.EmissaoPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<PedidoResumoModel> listar() {
         return pedidoResumoModelAssembler.toCollectionModel(pedidoRepository.findAll());
    }

    @GetMapping("/{pedidoId}")
    public PedidoModel buscar(@PathVariable Long pedidoId) {
        return pedidoModelAssembler.toModel(emissaoPedido.buscarOuFalhar(pedidoId));
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
}