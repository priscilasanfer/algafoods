package com.priscilasanfer.algafood.core.jackson;

import com.fasterxml.jackson.databind.module.SimpleModule;
import com.priscilasanfer.algafood.api.model.mixin.CidadeMixin;
import com.priscilasanfer.algafood.api.model.mixin.CozinhaMixin;
import com.priscilasanfer.algafood.domain.model.Cidade;
import com.priscilasanfer.algafood.domain.model.Cozinha;
import org.springframework.stereotype.Component;

@Component
public class JacksonMixinModule extends SimpleModule {

    public JacksonMixinModule() {
        setMixInAnnotation(Cozinha.class, CozinhaMixin.class);
        setMixInAnnotation(Cidade.class, CidadeMixin.class);
    }
}
