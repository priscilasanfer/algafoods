package com.priscilasanfer.algafood.api.controller;

import com.priscilasanfer.algafood.api.assembler.EstadoInputDisassembler;
import com.priscilasanfer.algafood.api.assembler.EstadoModelAssembler;
import com.priscilasanfer.algafood.api.model.EstadoModel;
import com.priscilasanfer.algafood.api.model.input.EstadoInput;
import com.priscilasanfer.algafood.domain.model.Estado;
import com.priscilasanfer.algafood.domain.repository.EstadoRepository;
import com.priscilasanfer.algafood.domain.service.CadastroEstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/estados")
public class EstadoController {

    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private CadastroEstadoService cadastroEstado;

    @Autowired
    private EstadoInputDisassembler estadoInputDisassembler;

    @Autowired
    private EstadoModelAssembler estadoModelAssembler;

    @GetMapping
    public List<EstadoModel> listar() {
        return estadoModelAssembler.toCollectionModel(estadoRepository.findAll());
    }

    @GetMapping("/{estadoId}")
    public EstadoModel buscar(@PathVariable Long estadoId) {
        return estadoModelAssembler.toModel(cadastroEstado.buscarOuFalhar(estadoId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EstadoModel adicionar(@RequestBody @Valid EstadoInput estadoInput) {
        Estado estado = estadoInputDisassembler.toDomainObject(estadoInput);
        estado = cadastroEstado.salvar(estado);
        return estadoModelAssembler.toModel(estado);
    }

    @PutMapping("/{estadoId}")
    public EstadoModel atualizar(@PathVariable Long estadoId,
                                 @RequestBody @Valid EstadoInput estadoInput) {
        Estado estadoAtual = cadastroEstado.buscarOuFalhar(estadoId);
        estadoInputDisassembler.copyToDomainObject(estadoInput, estadoAtual);
        estadoAtual = cadastroEstado.salvar(estadoAtual);
        return estadoModelAssembler.toModel(estadoAtual);
    }

    @DeleteMapping("/{estadoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long estadoId) {
        cadastroEstado.excluir(estadoId);
    }
}
