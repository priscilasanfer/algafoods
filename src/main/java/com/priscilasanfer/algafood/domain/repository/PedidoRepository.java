package com.priscilasanfer.algafood.domain.repository;

import com.priscilasanfer.algafood.domain.model.Pedido;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends CustomJpaRepository<Pedido, Long> {
}
