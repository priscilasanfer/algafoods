package com.priscilasanfer.algafood.infrastructure.repository.spec;

import com.priscilasanfer.algafood.domain.model.Restaurante;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class RestauranteSpecs {

    public static Specification<Restaurante> comFreteGratis() {
        return (root, criteriaQuery, builder) ->
                builder.equal(root.get("taxaFrete"), BigDecimal.ZERO);
    }

    public static Specification<Restaurante> comNomeSemelhante(String nome) {
        return (root, criteriaQuery, builder) ->
                builder.like(root.get("nome"), "%" + nome + "%");
    }
}
