package com.priscilasanfer.algafood.domain.listener;

import com.priscilasanfer.algafood.domain.event.PedidoConfirmadoEvent;
import com.priscilasanfer.algafood.domain.model.Pedido;
import com.priscilasanfer.algafood.domain.service.EnvioEmailService;
import com.priscilasanfer.algafood.domain.service.EnvioEmailService.Mensagem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class NotificacaoClientePedidoConfirmadoListener {

    @Autowired
    private EnvioEmailService envioEmailService;

    @TransactionalEventListener // evento é tratado depois que a transação for comitada e se der erro nao faz rollback e nesse caso nao envia o email
    public void aoConfirmarPedido(PedidoConfirmadoEvent event) {
        Pedido pedido = event.getPedido();

        var mensagem = Mensagem.builder()
                .assunto(pedido.getRestaurante().getNome() + "- Pedido confirmado.")
                .corpo("pedido-confirmado.html")
                .destinatario(pedido.getCliente().getEmail())
                .variavel("pedido", pedido)
                .build();

        envioEmailService.enviar(mensagem);
    }

}
