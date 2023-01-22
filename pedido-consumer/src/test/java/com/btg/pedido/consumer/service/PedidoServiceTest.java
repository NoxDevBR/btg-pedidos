package com.btg.pedido.consumer.service;


import com.btg.pedido.consumer.dto.Item;
import com.btg.pedido.consumer.model.PedidoModel;
import com.btg.pedido.consumer.dto.Pedido;
import com.btg.pedido.consumer.repository.PedidoRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class PedidoServiceTest {

    @InjectMocks
    private PedidoService pedidoService;

    @Mock
    private PedidoRepository pedidoRepository;

    @Test
    public void shouldPersistPedidoWhenSuccess() {
        final var pedido = buildPedido();

        when(pedidoRepository.findByCodigoPedido(any())).thenReturn(Optional.empty());

        pedidoService.handle(pedido);

        final var captor = ArgumentCaptor.forClass(PedidoModel.class);
        verify(pedidoRepository, times(1)).save(captor.capture());

        final var model = captor.getValue();
        assertNotNull(model);
        assertNotNull(model.getItens());
        assertEquals(pedido.getCodigoPedido(), model.getCodigoPedido());
        assertEquals(pedido.getCodigoCliente(), model.getCodigoCliente());
        assertEquals(2, model.getItens().size());

        final var itemCaderno = model.getItens().stream()
                .filter(item -> "caderno".equals(item.getProduto()))
                .collect(Collectors.toList());

        assertEquals(1, itemCaderno.size());
    }

    @Test
    public void shouldDiscardPedidoWhenDuplicated() {
        final var pedido = buildPedido();

        when(pedidoRepository.findByCodigoPedido(any())).thenReturn(Optional.of(new PedidoModel()));

        pedidoService.handle(pedido);

        verify(pedidoRepository, never()).save(any());
    }

    private Pedido buildPedido() {

        final var items = List.of(
                Item.builder()
                        .produto("lápis")
                        .preco(new BigDecimal("1.10"))
                        .quantidade(100L)
                        .build(),

                Item.builder()
                        .produto("caderno")
                        .preco(new BigDecimal("1.00"))
                        .quantidade(10L)
                        .build()
        );

        return Pedido.builder()
                .codigoPedido(1001L)
                .codigoCliente(1L)
                .itens(items)
                .build();
    }
}
