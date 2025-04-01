package com.github.integrador.PedidoEntradaParcela;

import java.util.Date;

public record PedidoEntradaParcelaGetDto(
        Integer id,
        Integer idPedidoEntrada,
        Double valor,
        Date dataVencimento,
        Integer status
) { }