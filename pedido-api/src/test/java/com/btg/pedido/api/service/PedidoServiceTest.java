package com.btg.pedido.api.service;

import com.btg.pedido.api.model.ItemModel;
import com.btg.pedido.api.model.PedidoModel;
import com.btg.pedido.api.repository.PedidoRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
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
    public void shouldReturnPedidoWithCorrectTotalAmount() {
        when(pedidoRepository.findByCodigoPedido(any())).thenReturn(Optional.of(buildPedidoModel()));

        final var pedidoOpt = pedidoService.get(1001L);

        verify(pedidoRepository, times(1)).findByCodigoPedido(any());

        assertTrue(pedidoOpt.isPresent());
        final var pedido = pedidoOpt.get();
        assertNotNull(pedido.getItens());
        assertEquals(1001L, pedido.getCodigoPedido());
        assertEquals(1L, pedido.getCodigoCliente());
        assertEquals(2, pedido.getItens().size());
        assertEquals(new BigDecimal(120).setScale(2), pedido.getValorTotal().setScale(2));
    }

    @Test
    public void shouldReturnEmptyWhenNotFound() {
        when(pedidoRepository.findByCodigoPedido(any())).thenReturn(Optional.empty());

        final var pedido = pedidoService.get(1001L);

        verify(pedidoRepository, times(1)).findByCodigoPedido(any());

        assertFalse(pedido.isPresent());
    }

    private PedidoModel buildPedidoModel() {

        final var items = List.of(
                ItemModel.builder()
                        .produto("lápis")
                        .preco(new BigDecimal("1.10"))
                        .quantidade(100L)
                        .build(),

                ItemModel.builder()
                        .produto("caderno")
                        .preco(new BigDecimal("1.00"))
                        .quantidade(10L)
                        .build()
        );

        return PedidoModel.builder()
                .codigoPedido(1001L)
                .codigoCliente(1L)
                .itens(items)
                .build();
    }
}
