package com.btg.pedido.consumer.consumer;

import com.btg.pedido.consumer.dto.Pedido;
import com.btg.pedido.consumer.service.PedidoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;


@Slf4j
@Component
@RequiredArgsConstructor
public class PedidoConsumer {

    private final PedidoService pedidoService;

    @RabbitListener(queues = {"${rabbitmq.queues.pedido}"})
    public void onMessage(@Payload final Pedido pedido) {
        pedidoService.handle(pedido);
    }
}
