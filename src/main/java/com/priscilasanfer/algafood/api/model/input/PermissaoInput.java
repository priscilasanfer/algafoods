package com.priscilasanfer.algafood.api.model.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
public class PermissaoInput {

    @NotBlank
    private String nome;

    @NotBlank
    private String descricao;

}