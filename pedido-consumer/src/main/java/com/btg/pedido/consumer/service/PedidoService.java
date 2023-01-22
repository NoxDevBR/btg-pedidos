package com.btg.pedido.consumer.service;

import com.btg.pedido.consumer.mapper.PedidoModelMapper;
import com.btg.pedido.consumer.repository.PedidoRepository;
import com.btg.pedido.consumer.dto.Pedido;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PedidoService {

    private final PedidoRepository repository;

    public void handle(final Pedido pedido) {
        final var codigoPedido = pedido.getCodigoPedido();
        if (repository.findByCodigoPedido(codigoPedido).isPresent()) {
            log.warn("m=handle status=discarded codigoPedido={} reason=duplicated", codigoPedido);
            return;
        }

        final var model = PedidoModelMapper.from(pedido);
        repository.save(model);

        log.info("m=handle status=created codigoPedido={}", codigoPedido);
    }
}
