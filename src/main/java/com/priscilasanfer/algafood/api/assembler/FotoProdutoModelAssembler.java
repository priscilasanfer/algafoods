package com.priscilasanfer.algafood.api.assembler;

import com.priscilasanfer.algafood.api.model.FotoProdutoModel;
import com.priscilasanfer.algafood.domain.model.FotoProduto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FotoProdutoModelAssembler {
    @Autowired
    private ModelMapper modelMapper;

    public FotoProdutoModel toModel(FotoProduto foto) {
        return modelMapper.map(foto, FotoProdutoModel.class);
    }
}
