package com.priscilasanfer.algafood.domain.service;

import com.priscilasanfer.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.priscilasanfer.algafood.domain.model.Cozinha;
import com.priscilasanfer.algafood.domain.model.Restaurante;
import com.priscilasanfer.algafood.domain.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CadastroRestauranteService {

    private static final String MSG_RESTAURANTE_NAO_ENCONTRADO = "Não existe um cadastro de restaurante com código %d";

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private CadastroCozinhaService cozinhaService;

    public Restaurante salvar(Restaurante restaurante) {
        Long cozinhaId = restaurante.getCozinha().getId();
        Cozinha cozinha = cozinhaService.buscasOuFalhar(cozinhaId);
        restaurante.setCozinha(cozinha);

        return restauranteRepository.save(restaurante);
    }

    public Restaurante buscarOuFalhar (Long id){
        return restauranteRepository.findById(id).orElseThrow(()-> new EntidadeNaoEncontradaException(String.format(MSG_RESTAURANTE_NAO_ENCONTRADO, id)));
    }

}
