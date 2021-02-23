package com.priscilasanfer.algafood.api.controller;

import com.priscilasanfer.algafood.domain.model.Cozinha;
import com.priscilasanfer.algafood.domain.repository.CozinhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/teste")
public class TestaController {

    @Autowired
    private CozinhaRepository cozinhaRepository;

//    @GetMapping("cozinha/por-nome")
//    public List<Cozinha> cozinhasPorNome(@RequestParam String nome){
//        return cozinhaRepository.consultarPorNome(nome);
//
//    }
}
