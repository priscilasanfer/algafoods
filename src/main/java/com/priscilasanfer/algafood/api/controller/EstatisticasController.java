package com.priscilasanfer.algafood.api.controller;

import com.priscilasanfer.algafood.domain.filter.VendasDiariasFilter;
import com.priscilasanfer.algafood.domain.model.dto.VendaDiarias;
import com.priscilasanfer.algafood.domain.service.VendaQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/estatisticas")
public class EstatisticasController {

    @Autowired
    private VendaQueryService vendaQueryService;

    @GetMapping("/vendas-diarias")
    public List<VendaDiarias> consultarVendasDiarias(VendasDiariasFilter filter,
                                                     @RequestParam(required = false, defaultValue = "+00:00") String timeOffset) {
        return vendaQueryService.consultarVendasDiarias(filter, timeOffset);
    }
}
