package com.priscilasanfer.algafood.api.model.mixin;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.priscilasanfer.algafood.domain.model.Restaurante;

import java.util.List;

@JsonRootName("cozinha")
public abstract class CozinhaMixin {

    @JsonIgnore
    private List<Restaurante> restaurantes;
}
