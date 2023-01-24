package com.btg.pedido.api.controller;

import com.btg.pedido.api.dto.Pedido;
import com.btg.pedido.api.service.PedidoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/pedido")
@RequiredArgsConstructor
@Api(tags = "Pedido")
public class PedidoController {

    private final PedidoService pedidoService;

    @GetMapping("/{codigoPedido}")
    @ApiOperation(value = "Consultar todas informações sobre pedido")
    public ResponseEntity<Pedido> get(@PathVariable final Long codigoPedido) {
        return pedidoService.get(codigoPedido)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{codigoPedido}/total")
    @ApiOperation(value = "Consultar valor do pedido")
    public ResponseEntity<BigDecimal> getValorTotal(@PathVariable final Long codigoPedido) {
        return pedidoService.get(codigoPedido)
                .map(pedido -> pedido.getValorTotal())
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/cliente/{codigoCliente}")
    @ApiOperation(value = "Consultar todos os pedidos por cliente")
    public List<Pedido> listByClient(@PathVariable final Long codigoCliente) {
        return pedidoService.listByClient(codigoCliente);
    }

    @GetMapping("/cliente/{codigoCliente}/quantidade")
    @ApiOperation(value = "Consultar a quantidade de pedidos de um cliente")
    public Long countByClient(@PathVariable final Long codigoCliente) {
        return pedidoService.countByClient(codigoCliente);
    }

}
