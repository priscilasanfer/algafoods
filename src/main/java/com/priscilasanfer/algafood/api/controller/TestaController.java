package com.priscilasanfer.algafood.api.controller;

import com.priscilasanfer.algafood.domain.model.Cozinha;
import com.priscilasanfer.algafood.domain.model.Restaurante;
import com.priscilasanfer.algafood.domain.repository.CozinhaRepository;
import com.priscilasanfer.algafood.domain.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/teste")
public class TestaController {

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @Autowired
    private RestauranteRepository restauranteRepository;

    @GetMapping("cozinha/por-nome")
    public List<Cozinha> cozinhasPorNome(String nome){
        return cozinhaRepository.findByNome(nome);
    }

    @GetMapping("/restaurantes/por-taxa-frete")
    public List<Restaurante> restaurantesPorTaxa(BigDecimal taxaInicial, BigDecimal taxaFinal){
        return restauranteRepository.findByTaxaFreteBetween(taxaInicial, taxaFinal);
    }

    @GetMapping("/restaurantes/por-nome")
    public List<Restaurante> restaurantesPorNome(String nome, Long cozinhaId){
        return restauranteRepository.findByNomeContainingAndCozinhaId(nome, cozinhaId);
    }
}
