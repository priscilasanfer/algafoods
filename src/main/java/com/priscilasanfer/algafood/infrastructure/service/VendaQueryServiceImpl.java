package com.priscilasanfer.algafood.infrastructure.service;

import com.priscilasanfer.algafood.domain.filter.VendasDiariasFilter;
import com.priscilasanfer.algafood.domain.model.Pedido;
import com.priscilasanfer.algafood.domain.model.dto.VendaDiarias;
import com.priscilasanfer.algafood.domain.service.VendaQueryService;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;

@Repository
public class VendaQueryServiceImpl implements VendaQueryService {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<VendaDiarias> consultarVendasDiarias(VendasDiariasFilter filter) {
        var builder = manager.getCriteriaBuilder();
        var query = builder.createQuery(VendaDiarias.class);
        var root = query.from(Pedido.class);

        var functionDateDataCriacao = builder.function(
                "date", Date.class, root.get("dataCriacao"));

        var selection = builder.construct(VendaDiarias.class,
                functionDateDataCriacao,
                builder.count(root.get("id")),
                builder.sum(root.get("valorTotal")));

        query.select(selection);
        query.groupBy(functionDateDataCriacao);


        return manager.createQuery(query).getResultList();
    }
}
