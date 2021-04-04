package com.priscilasanfer.algafood.service;

import com.priscilasanfer.algafood.domain.model.Cozinha;

public class CozinhaFixture {

    public static Cozinha cozinhaRequest(){
        Cozinha cozinha = new Cozinha();
        cozinha.setNome("Uruguaia");
        return cozinha;
    }
}
