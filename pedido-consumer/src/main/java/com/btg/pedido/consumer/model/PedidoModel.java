package com.btg.pedido.consumer.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "pedido")
public class PedidoModel {
    @Id
    private String id;
    private Long codigoPedido;
    private Long codigoCliente;
    private List<ItemModel> itens;
}
