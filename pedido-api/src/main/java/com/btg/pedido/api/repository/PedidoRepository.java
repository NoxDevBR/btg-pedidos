package com.btg.pedido.api.repository;

import com.btg.pedido.api.model.PedidoModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PedidoRepository extends MongoRepository<PedidoModel, Long> {

    Optional<PedidoModel> findByCodigoPedido(Long codigoPedido);

    List<PedidoModel> findAllByCodigoCliente(Long codigoCliente);

    Long countByCodigoCliente(Long codigoCliente);
}
