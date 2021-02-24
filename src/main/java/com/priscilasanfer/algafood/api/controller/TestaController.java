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
import java.util.Optional;

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

    @GetMapping("/restaurantes/por-taxa-frete")
    public List<Restaurante> restaurantesPorTaxaUsandoInterface (String nome, BigDecimal taxaInicial, BigDecimal taxaFinal){
        return restauranteRepository.find(nome, taxaInicial, taxaFinal);
    }

    @GetMapping("/restaurantes/por-nome")
    public List<Restaurante> restaurantesPorNome(String nome, Long cozinhaId){
        return restauranteRepository.consultarPorNome(nome, cozinhaId);
    }

    @GetMapping("/restaurantes/primeiro-por-nome")
    public Optional<Restaurante> restaurantePrimeiroPorNome(String nome){
        return restauranteRepository.findFirstByNomeContaining(nome);
    }

    @GetMapping("/restaurantes/top2-por-nome")
    public List<Restaurante> restaurantesTop2PorNome(String nome){
        return restauranteRepository.findTop2ByNomeContaining(nome);
    }

    @GetMapping("cozinha/exists")
    public boolean cozinhaExist(String nome){
        return cozinhaRepository.existsByNome(nome);
    }

    @GetMapping("cozinha/count-por-cozinha")
    public int cozinhaCount(Long cozinhaId){
        return cozinhaRepository.countByCozinhaId(cozinhaId);
    }
}
