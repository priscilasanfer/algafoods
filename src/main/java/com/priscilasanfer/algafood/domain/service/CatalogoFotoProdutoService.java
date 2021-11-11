package com.priscilasanfer.algafood.domain.service;

import com.priscilasanfer.algafood.domain.model.FotoProduto;
import com.priscilasanfer.algafood.domain.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.priscilasanfer.algafood.domain.service.FotoStorageService.NovaFoto;

import java.io.InputStream;
import java.util.Optional;

@Service
public class CatalogoFotoProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private FotoStorageService fotoStorage;

    @Transactional
    public FotoProduto salvar(FotoProduto foto, InputStream dadosArquivo) {
        Long restauranteId = foto.getRestauranteId();
        Long produtoId = foto.getProduto().getId();
        String nomeNovoArquivo = fotoStorage.gerarNomeArquivo(foto.getNomeArquivo());

        Optional<FotoProduto> fotoExistente = produtoRepository.findFotoById(restauranteId, produtoId);

        fotoExistente.ifPresent(fotoProduto -> produtoRepository.delete(fotoExistente.get()));

        foto.setNomeArquivo(nomeNovoArquivo);
        foto = produtoRepository.save(foto);
        produtoRepository.flush();

        NovaFoto novaFoto =  NovaFoto.builder()
                .nomeArquivo(foto.getNomeArquivo())
                .inputStream(dadosArquivo)
                .build();

        fotoStorage.armazenar(novaFoto);

        return foto;
    }
}
