package com.github.integrador.PedidoSaida;

import lombok.Builder;

import java.util.Date;

@Builder
public record PedidoSaidaGetDto(
        Integer id,
        Integer idCliente,
        Integer idVendedor,
        Date dataCriacao,
        Date dataVigencia,
        Date dataEntregaPrevista,
        Date dataEntregaReal,
        Integer statusEntrega,
        Integer statusPagamento,
        Double valorTotal,
        Double valorFrete
) { }