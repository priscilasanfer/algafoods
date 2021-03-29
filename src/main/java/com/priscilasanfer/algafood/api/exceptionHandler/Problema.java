package com.priscilasanfer.algafood.api.exceptionHandler;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class Problema {
    private String mensagem;
    private LocalDateTime dataHora;
}
