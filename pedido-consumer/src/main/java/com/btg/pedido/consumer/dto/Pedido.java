package com.btg.pedido.consumer.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Getter
@Builder
@Jacksonized
@RequiredArgsConstructor
public class Pedido {
    private final Long codigoPedido;
    private final Long codigoCliente;
    private final List<Item> itens;
}
