package com.github.integrador.PedidoEntradaParcela;

import lombok.Data;

public record PedidoEntradaParcelaGetDto(
        Integer id,
        Integer idPedidoEntrada,
        Double valor,
        Data dataVencimento,
        Integer status
) { }