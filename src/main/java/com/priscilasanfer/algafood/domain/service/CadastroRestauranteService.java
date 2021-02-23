package com.priscilasanfer.algafood.domain.service;

import com.priscilasanfer.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.priscilasanfer.algafood.domain.model.Cozinha;
import com.priscilasanfer.algafood.domain.model.Restaurante;
import com.priscilasanfer.algafood.domain.repository.CozinhaRepository;
import com.priscilasanfer.algafood.domain.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CadastroRestauranteService {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private CozinhaRepository cozinhaRepository;

    public Restaurante salvar(Restaurante restaurante) {
        Long cozinhaId = restaurante.getCozinha().getId();
        Cozinha cozinha = cozinhaRepository.buscar(cozinhaId);

        if (cozinha == null) {
            throw new EntidadeNaoEncontradaException(
                    String.format("Não existe cadastro de cozinha com código %d", cozinhaId));
        }

        restaurante.setCozinha(cozinha);

        return restauranteRepository.salvar(restaurante);
    }

}
