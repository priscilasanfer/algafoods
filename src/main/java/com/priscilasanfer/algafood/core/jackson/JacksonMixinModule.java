package com.priscilasanfer.algafood.core.jackson;

import com.fasterxml.jackson.databind.module.SimpleModule;
import com.priscilasanfer.algafood.api.model.mixin.RestauranteMixin;
import com.priscilasanfer.algafood.domain.model.Restaurante;
import org.springframework.stereotype.Component;

@Component
public class JacksonMixinModule extends SimpleModule {

    public JacksonMixinModule() {
        setMixInAnnotation(Restaurante.class, RestauranteMixin.class);
    }
}
