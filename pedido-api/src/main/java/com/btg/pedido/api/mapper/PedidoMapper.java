package com.btg.pedido.api.mapper;

import com.btg.pedido.api.dto.Item;
import com.btg.pedido.api.dto.Pedido;
import com.btg.pedido.api.model.PedidoModel;

import java.math.BigDecimal;
import java.util.stream.Collectors;

public class PedidoMapper {
    public static Pedido from(final PedidoModel pedido) {
        final var itens = pedido.getItens()
                .stream()
                .map(item -> Item.builder()
                        .preco(item.getPreco())
                        .produto(item.getProduto())
                        .quantidade(item.getQuantidade())
                        .build())
                .collect(Collectors.toList());

        final var totalAmount = itens.stream()
                .map(item -> item.getPreco().multiply(BigDecimal.valueOf(item.getQuantidade())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return Pedido.builder()
                .codigoCliente(pedido.getCodigoCliente())
                .codigoPedido(pedido.getCodigoPedido())
                .itens(itens)
                .valorTotal(totalAmount)
                .build();
    }
}
