package com.priscilasanfer.algafood.jpa;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.priscilasanfer.algafood.AlgafoodApiApplication;
import com.priscilasanfer.algafood.domain.model.Cozinha;
import com.priscilasanfer.algafood.domain.repository.CozinhaRepository;

public class BuscaCozinhaMain {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        CozinhaRepository cozinhaRepository = applicationContext.getBean(CozinhaRepository.class);

        Cozinha cozinha = cozinhaRepository.buscar(1L);

        System.out.println(cozinha.getNome());
    }

}
