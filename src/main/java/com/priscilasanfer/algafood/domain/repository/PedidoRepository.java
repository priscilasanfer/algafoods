package com.priscilasanfer.algafood.domain.repository;

import com.priscilasanfer.algafood.domain.model.Pedido;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PedidoRepository extends CustomJpaRepository<Pedido, Long> {
    @Query("from Pedido p join fetch p.cliente join fetch p.restaurante r join fetch r.cozinha")
    List<Pedido> findAll();
}
