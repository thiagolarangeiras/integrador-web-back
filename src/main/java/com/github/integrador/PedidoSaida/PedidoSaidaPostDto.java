package com.github.integrador.PedidoSaida;

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
        Integer statusEntrega,
        Integer statusPagamento,
        Double valorTotal,
        Double valorFrete
) { }