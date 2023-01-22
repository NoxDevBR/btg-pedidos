package com.btg.pedido.consumer.mapper;

import com.btg.pedido.consumer.dto.Pedido;
import com.btg.pedido.consumer.model.ItemModel;
import com.btg.pedido.consumer.model.PedidoModel;

import java.util.stream.Collectors;

public class PedidoModelMapper {
    public static PedidoModel from(final Pedido pedido) {
        final var itens = pedido.getItens()
                .stream()
                .map(item -> ItemModel.builder()
                        .preco(item.getPreco())
                        .produto(item.getProduto())
                        .quantidade(item.getQuantidade())
                        .build())
                .collect(Collectors.toList());

        return PedidoModel.builder()
                .codigoCliente(pedido.getCodigoCliente())
                .codigoPedido(pedido.getCodigoPedido())
                .itens(itens)
                .build();
    }
}
