package com.github.integrador.dtos;

import java.util.Date;

public record PedidoEntradaGetDto(
        Integer id,
        Integer idFornecedor,
        Date dataCriacao,
        Date dataVigencia,
        Date dataEntregaPrevista,
        Date dataEntregaReal,
        Integer statusEntrega,
        Integer statusPagamento,
        Double valorTotal,
        Double valorFrete
){ }