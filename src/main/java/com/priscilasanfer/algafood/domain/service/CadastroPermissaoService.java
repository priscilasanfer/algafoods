package com.priscilasanfer.algafood.domain.service;

import com.priscilasanfer.algafood.domain.exception.PermissaoNaoEncontradaException;
import com.priscilasanfer.algafood.domain.model.Permissao;
import com.priscilasanfer.algafood.domain.repository.PermissaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CadastroPermissaoService {

    @Autowired
    private PermissaoRepository permissaoRepository;

    public Permissao buscarOuFalhar(Long permissaoId) {
        return permissaoRepository.findById(permissaoId)
                .orElseThrow(() -> new PermissaoNaoEncontradaException(permissaoId));
    }
}
