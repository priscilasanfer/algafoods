package com.priscilasanfer.algafood.api.controller;

import com.priscilasanfer.algafood.api.assembler.FotoProdutoAssember;
import com.priscilasanfer.algafood.api.model.FotoProdutoModel;
import com.priscilasanfer.algafood.api.model.input.FotoProdutoInput;
import com.priscilasanfer.algafood.domain.model.FotoProduto;
import com.priscilasanfer.algafood.domain.model.Produto;
import com.priscilasanfer.algafood.domain.service.CadastroProdutoService;
import com.priscilasanfer.algafood.domain.service.CatalogoFotoProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@RestController
@RequestMapping("/restaurantes/{restauranteId}/produtos/{produtoId}/foto")
public class RestauranteProdutoFotoController {

    @Autowired
    private CatalogoFotoProdutoService catalogoFotoProdutoService;

    @Autowired
    private CadastroProdutoService cadastroProdutoService;

    @Autowired
    private FotoProdutoAssember fotoProdutoAssember;

    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public FotoProdutoModel atualizarFoto(@PathVariable Long restauranteId, @PathVariable Long produtoId,
                                          @Valid FotoProdutoInput fotoProdutoInput) {

        Produto produto = cadastroProdutoService.buscarOuFalhar(restauranteId, produtoId);

        MultipartFile arquivo = fotoProdutoInput.getArquivo();

        FotoProduto foto = new FotoProduto();
        foto.setProduto(produto);
        foto.setDescricao(fotoProdutoInput.getDescricao());
        foto.setContentType(arquivo.getContentType());
        foto.setTamanho(arquivo.getSize());
        foto.setNomeArquivo(arquivo.getOriginalFilename());

        FotoProduto fotoSalva = catalogoFotoProdutoService.salvar(foto);

        return fotoProdutoAssember.toModel(fotoSalva);

    }
}
