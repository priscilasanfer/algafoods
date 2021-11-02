package com.priscilasanfer.algafood.domain.service;

import com.priscilasanfer.algafood.domain.filter.VendaDiariaFilter;

public interface VendaReportService {
    byte[] emitirVendasDiarias(VendaDiariaFilter filter, String timeOffset);
}
