package com.priscilasanfer.algafood.core;

import com.priscilasanfer.algafood.api.model.EnderecoModel;
import com.priscilasanfer.algafood.api.model.input.ItemPedidoInput;
import com.priscilasanfer.algafood.domain.model.Endereco;
import com.priscilasanfer.algafood.domain.model.ItemPedido;
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

        modelMapper.createTypeMap(ItemPedidoInput.class, ItemPedido.class)
                .addMappings(mapper -> mapper.skip(ItemPedido::setId));

        return modelMapper;
    }
}
