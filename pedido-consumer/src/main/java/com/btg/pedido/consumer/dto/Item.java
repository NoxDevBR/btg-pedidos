package com.btg.pedido.consumer.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.jackson.Jacksonized;

import java.math.BigDecimal;

@Getter
@Builder
@Jacksonized
@RequiredArgsConstructor
public class Item {
    private final String produto;
    private final Long quantidade;
    private final BigDecimal preco;
}
