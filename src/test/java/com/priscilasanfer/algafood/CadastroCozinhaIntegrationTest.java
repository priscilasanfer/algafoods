package com.priscilasanfer.algafood;

import com.priscilasanfer.algafood.domain.exception.CozinhaNaoEncontradaException;
import com.priscilasanfer.algafood.domain.exception.EntidadeEmUsoException;
import com.priscilasanfer.algafood.domain.model.Cozinha;
import com.priscilasanfer.algafood.domain.service.CadastroCozinhaService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolationException;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CadastroCozinhaIntegrationTest {

    @Autowired
    private CadastroCozinhaService cadastroCozinha;

    @Test
    public void deveAtribuirIdQuandoCadastrarCozinhaComSucesso() {
        //Cenario
        Cozinha novaCozinha = new Cozinha();
        novaCozinha.setNome("Tailandeza");

        //Ação
        novaCozinha = cadastroCozinha.salvar(novaCozinha);

        //Validação
        assertThat(novaCozinha).isNotNull();
        assertThat(novaCozinha.getId()).isNotNull();

    }

    @Test(expected = ConstraintViolationException.class)
    public void deveFalharQuandoCadastrarCozinhaSemNome(){
        Cozinha novaCozinha = new Cozinha();
        novaCozinha.setNome(null);

        cadastroCozinha.salvar(novaCozinha);
    }

    @Test(expected = EntidadeEmUsoException.class)
    public void deveFalharQuandoExcluirCozinhaEmUso(){
        cadastroCozinha.excluir(1L);
    }

    @Test(expected = CozinhaNaoEncontradaException.class)
    public void deveFalharQuandoExcluirCozinhaInexistente(){
        cadastroCozinha.excluir(1000L);
    }

}
