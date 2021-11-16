package com.priscilasanfer.algafood.domain.event;

import com.priscilasanfer.algafood.domain.model.Pedido;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PedidoCanceladoEvent {
    private Pedido pedido;
}
