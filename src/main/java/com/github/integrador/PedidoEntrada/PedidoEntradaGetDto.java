package com.github.integrador.PedidoEntrada;

import com.github.integrador.enums.StatusEntrega;
import com.github.integrador.enums.StatusPagamento;
import com.github.integrador.enums.StatusPedido;

import java.util.Date;

public record PedidoEntradaGetDto(
        Integer id,
        Integer idFornecedor,
        Date dataCriacao,
        Date dataVigencia,
        Date dataEntregaPrevista,
        Date dataEntregaReal,
        StatusPedido status,
        StatusEntrega statusEntrega,
        StatusPagamento statusPagamento,
        Double valorTotal,
        Double valorFrete
){ }