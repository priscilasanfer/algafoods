package com.priscilasanfer.algafood.domain.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@AllArgsConstructor
@Getter
@Setter
public class VendaDiarias {
    private Date date;
    private Long totalVendas;
    private BigDecimal totalFaturado;
}
