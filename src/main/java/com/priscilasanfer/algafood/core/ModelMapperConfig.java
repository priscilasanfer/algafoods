package com.priscilasanfer.algafood.core;

import com.priscilasanfer.algafood.api.model.EnderecoModel;
import com.priscilasanfer.algafood.domain.model.Endereco;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        var modelMapper = new ModelMapper();

        // modelMapper.createTypeMap(Restaurante.class, RestauranteModel.class).addMapping(Restaurante::getTaxaFrete, RestauranteModel::setPrecoFrete);

        var enderecoToEnderecoModelTypedMap = modelMapper
                .createTypeMap(Endereco.class, EnderecoModel.class)
                .addMapping(enderecoSrc -> enderecoSrc.getCidade().getEstado().getNome(),
                        (enderecoModelDest, value) -> enderecoModelDest.getCidade().setEstado(String.valueOf(value)));

        return modelMapper;
    }
}
