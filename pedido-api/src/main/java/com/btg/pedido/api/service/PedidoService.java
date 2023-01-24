package com.btg.pedido.api.service;

import com.btg.pedido.api.dto.Pedido;
import com.btg.pedido.api.mapper.PedidoMapper;
import com.btg.pedido.api.repository.PedidoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class PedidoService {

    private final PedidoRepository repository;

    public Optional<Pedido> get(final Long codigoPedido) {
        final var pedidoModel = repository.findByCodigoPedido(codigoPedido);
        return pedidoModel.map(PedidoMapper::from);
    }

    public List<Pedido> listByClient(final Long codigoCliente) {
        final var pedidoModelList = repository.findAllByCodigoCliente(codigoCliente);
        return pedidoModelList.stream()
                .map(PedidoMapper::from)
                .collect(Collectors.toList());
    }

    public Long countByClient(final Long codigoCliente) {
        return repository.countByCodigoCliente(codigoCliente);
    }
}
