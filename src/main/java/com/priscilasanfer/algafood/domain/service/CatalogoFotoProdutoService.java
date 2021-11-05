package com.priscilasanfer.algafood.domain.service;

import com.priscilasanfer.algafood.domain.model.FotoProduto;
import com.priscilasanfer.algafood.domain.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CatalogoFotoProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Transactional
    public FotoProduto salvar(FotoProduto fotoProduto){

        return produtoRepository.save(fotoProduto);
    }


}
