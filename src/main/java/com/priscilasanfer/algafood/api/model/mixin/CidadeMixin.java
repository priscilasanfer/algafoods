package com.priscilasanfer.algafood.api.model.mixin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.priscilasanfer.algafood.domain.model.Estado;

public abstract class CidadeMixin {

    @JsonIgnoreProperties(value = "nome", allowGetters = true )
    private Estado estado;
}
