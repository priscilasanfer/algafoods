package com.priscilasanfer.algafood.service;

import com.priscilasanfer.algafood.domain.repository.CozinhaRepository;
import com.priscilasanfer.algafood.domain.service.CadastroCozinhaService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class CozinhaServiceTest {

    @Mock
    private CozinhaRepository cozinhaRepository;

    @InjectMocks
    private CadastroCozinhaService cozinhaService;


    @Test
    public void deveCadastrarCozinhaComSucesso(){
        cozinhaService.salvar(CozinhaFixture.cozinhaRequest());
        verify(cozinhaRepository, times(1)).save(any());
    }

    @Test
    public void deveExcluirComSucesso(){
        cozinhaService.excluir(anyLong());
        verify(cozinhaRepository, Mockito.times(1)).deleteById(anyLong());
    }

}
