package com.btg.pedido.api.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Getter
@Builder
@RequiredArgsConstructor
public class Item {
    private final String produto;
    private final Long quantidade;
    private final BigDecimal preco;
}
