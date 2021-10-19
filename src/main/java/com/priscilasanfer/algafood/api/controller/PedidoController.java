package com.priscilasanfer.algafood.api.controller;

import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
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
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.json.MappingJacksonValue;
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
    public MappingJacksonValue listar(@RequestParam(required = false) String campos) {
        List<PedidoResumoModel> pedidosModel = pedidoResumoModelAssembler.toCollectionModel(pedidoRepository.findAll());
        MappingJacksonValue pedidosWrapper = new MappingJacksonValue(pedidosModel);

        SimpleFilterProvider filterProvider = new SimpleFilterProvider();
        filterProvider.addFilter("pedidoFilter", SimpleBeanPropertyFilter.serializeAll());

        if (StringUtils.isNotBlank(campos)) {
            filterProvider.addFilter("pedidoFilter", SimpleBeanPropertyFilter.filterOutAllExcept(campos.split(",")));
        }

        pedidosWrapper.setFilters(filterProvider);

        return pedidosWrapper;
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
}