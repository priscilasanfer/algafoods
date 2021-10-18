package com.priscilasanfer.algafood.api.model.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class GrupoInput {
    @NotBlank
    private String nome;
}