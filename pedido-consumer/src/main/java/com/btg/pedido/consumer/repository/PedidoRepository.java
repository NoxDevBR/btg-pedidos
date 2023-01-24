package com.btg.pedido.consumer.repository;

import com.btg.pedido.consumer.model.PedidoModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PedidoRepository extends MongoRepository<PedidoModel, Long> {
    Optional<PedidoModel> findByCodigoPedido(Long codigoPedido);
}
