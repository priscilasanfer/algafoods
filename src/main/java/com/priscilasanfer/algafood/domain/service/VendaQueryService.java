package com.priscilasanfer.algafood.domain.service;

import com.priscilasanfer.algafood.domain.filter.VendasDiariasFilter;
import com.priscilasanfer.algafood.domain.model.dto.VendaDiarias;

import java.util.List;

public interface VendaQueryService {

    List<VendaDiarias> consultarVendasDiarias (VendasDiariasFilter filter, String timeOffset);
}
