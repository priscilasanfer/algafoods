package com.priscilasanfer.algafood.api.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.priscilasanfer.algafood.api.assembler.RestauranteInputDisassembler;
import com.priscilasanfer.algafood.api.assembler.RestauranteModelAssembler;
import com.priscilasanfer.algafood.api.model.RestauranteModel;
import com.priscilasanfer.algafood.api.model.input.RestauranteInput;
import com.priscilasanfer.algafood.api.model.view.RestauranteView;
import com.priscilasanfer.algafood.domain.exception.CidadeNaoEncontradaException;
import com.priscilasanfer.algafood.domain.exception.CozinhaNaoEncontradaException;
import com.priscilasanfer.algafood.domain.exception.NegocioException;
import com.priscilasanfer.algafood.domain.exception.RestauranteNaoEncontradoException;
import com.priscilasanfer.algafood.domain.model.Restaurante;
import com.priscilasanfer.algafood.domain.repository.RestauranteRepository;
import com.priscilasanfer.algafood.domain.service.CadastroRestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/restaurantes")
public class RestauranteController {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private CadastroRestauranteService cadastroRestaurante;

    @Autowired
    private RestauranteModelAssembler restauranteModelAssembler;

    @Autowired
    private RestauranteInputDisassembler restauranteInputDisassembler;

    @GetMapping
    @JsonView(RestauranteView.Resumo.class)
    public List<RestauranteModel> listar() {
        return restauranteModelAssembler.toCollectionModel(restauranteRepository.findAll());
    }

    @JsonView(RestauranteView.ApenasNome.class)
    @GetMapping(params = "projecao=apenas-nome")
    public List<RestauranteModel> listarApenasNomes() {
        return listar();
    }

    @GetMapping("/{restauranteId}")
    public RestauranteModel buscar(@PathVariable Long restauranteId) {
        Restaurante restaurante = cadastroRestaurante.buscarOuFalhar(restauranteId);

        return restauranteModelAssembler.toModel(restaurante);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RestauranteModel adicionar(@RequestBody @Valid RestauranteInput restauranteInput) {
        try {
            Restaurante restaurante = restauranteInputDisassembler.toDomainObject(restauranteInput);
            return restauranteModelAssembler.toModel(cadastroRestaurante.salvar(restaurante));
        } catch (CozinhaNaoEncontradaException | CidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    @PutMapping("/{restauranteId}")
    public RestauranteModel atualizar(@PathVariable Long restauranteId,
                                 @RequestBody @Valid RestauranteInput restauranteInput) {
        try {
            Restaurante restauranteAtual = cadastroRestaurante.buscarOuFalhar(restauranteId);

            restauranteInputDisassembler.copyToDomainObject(restauranteInput, restauranteAtual);

            return restauranteModelAssembler.toModel(cadastroRestaurante.salvar(restauranteAtual));
        } catch (CozinhaNaoEncontradaException | CidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    @PutMapping("/{restauranteId}/ativo")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void ativar(@PathVariable Long restauranteId){
        cadastroRestaurante.ativar(restauranteId);
    }

    @DeleteMapping("/{restauranteId}/ativo")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void inativar(@PathVariable Long restauranteId){
        cadastroRestaurante.inativar(restauranteId);
    }

    @PutMapping("/{restauranteId}/fechamento")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void fechar(@PathVariable Long restauranteId){
        cadastroRestaurante.fechar(restauranteId);
    }

    @PutMapping("/{restauranteId}/abertura")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void abrir(@PathVariable Long restauranteId){
        cadastroRestaurante.abrir(restauranteId);
    }

    @PutMapping("/ativacoes")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void ativarMultiplo(@RequestBody List<Long> restauranteIds){
        try {
            cadastroRestaurante.ativar(restauranteIds);
        }catch (RestauranteNaoEncontradoException e){
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @PutMapping("/inativacoes")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void inativarMultiplo(@RequestBody List<Long> restauranteIds){
        try {
            cadastroRestaurante.inativar(restauranteIds);
        }catch (RestauranteNaoEncontradoException e){
            throw new NegocioException(e.getMessage(), e);
        }
    }

}
