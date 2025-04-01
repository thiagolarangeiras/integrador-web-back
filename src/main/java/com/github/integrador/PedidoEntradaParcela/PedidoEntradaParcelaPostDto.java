package com.github.integrador.PedidoEntradaParcela;

import lombok.Data;

public record PedidoEntradaParcelaPostDto(
        Integer idPedidoEntrada,
        Double valor,
        Data dataVencimento,
        Integer status
) { }