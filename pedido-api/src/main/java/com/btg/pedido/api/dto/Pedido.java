package com.btg.pedido.api.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Builder
@RequiredArgsConstructor
public class Pedido {
    private final Long codigoPedido;
    private final Long codigoCliente;
    private final List<Item> itens;
    private final BigDecimal valorTotal;
}
