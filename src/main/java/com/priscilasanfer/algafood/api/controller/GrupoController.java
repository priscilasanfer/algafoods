package com.priscilasanfer.algafood.api.controller;

import com.priscilasanfer.algafood.api.assembler.GrupoInputDisassembler;
import com.priscilasanfer.algafood.api.assembler.GrupoModelAssembler;
import com.priscilasanfer.algafood.api.model.GrupoModel;
import com.priscilasanfer.algafood.api.model.input.GrupoInput;
import com.priscilasanfer.algafood.domain.exception.GrupoNaoEncontradoException;
import com.priscilasanfer.algafood.domain.exception.NegocioException;
import com.priscilasanfer.algafood.domain.model.Grupo;
import com.priscilasanfer.algafood.domain.repository.GrupoRepository;
import com.priscilasanfer.algafood.domain.service.CadastroGrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/grupos")
public class GrupoController {

    @Autowired
    private GrupoRepository grupoRepository;

    @Autowired
    private CadastroGrupoService cadastroGrupo;

    @Autowired
    private GrupoInputDisassembler grupoInputDisassembler;

    @Autowired
    private GrupoModelAssembler grupoModelAssembler;

    @GetMapping
    public List<GrupoModel> listar() {
        return grupoModelAssembler.toCollectionModel(grupoRepository.findAll());
    }

    @GetMapping("/{grupoId}")
    public GrupoModel buscar(@PathVariable Long grupoId) {
        Grupo grupo = cadastroGrupo.buscarOuFalhar(grupoId);
        return grupoModelAssembler.toModel(grupo);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GrupoModel adicionar(@RequestBody @Valid GrupoInput grupoInput) {
        try {
            Grupo grupo = grupoInputDisassembler.toDomainObject(grupoInput);
            grupo = cadastroGrupo.salvar(grupo);
            return grupoModelAssembler.toModel(grupo);
        } catch (GrupoNaoEncontradoException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    @PutMapping("/{grupoId}")
    public GrupoModel atualizar(@PathVariable Long grupoId,
                                @RequestBody @Valid GrupoInput grupoInput) {
        try {
            Grupo grupoAtual = cadastroGrupo.buscarOuFalhar(grupoId);
            grupoInputDisassembler.copyToDomainObject(grupoInput, grupoAtual);
            grupoAtual = cadastroGrupo.salvar(grupoAtual);
            return grupoModelAssembler.toModel(grupoAtual);
        } catch (GrupoNaoEncontradoException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    @DeleteMapping("/{grupoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long grupoId) {
        cadastroGrupo.excluir(grupoId);
    }

}
