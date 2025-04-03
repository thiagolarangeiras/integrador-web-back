package com.github.integrador.PedidoSaida;

import com.github.integrador.enums.StatusEntrega;
import com.github.integrador.enums.StatusPagamento;
import com.github.integrador.enums.StatusPedido;
import lombok.Builder;

import java.util.Date;

@Builder
public record PedidoSaidaPostDto(
        Integer idCliente,
        Integer idVendedor,
        Date dataCriacao,
        Date dataVigencia,
        Date dataEntregaPrevista,
        Date dataEntregaReal,
        StatusPedido status,
        StatusEntrega statusEntrega,
        StatusPagamento statusPagamento,
        Double valorTotal,
        Double valorFrete
) { }