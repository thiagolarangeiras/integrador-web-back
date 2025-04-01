package com.github.integrador.PedidoEntradaParcela;

import java.util.Date;

public record PedidoEntradaParcelaPostDto(
        Integer idPedidoEntrada,
        Double valor,
        Date dataVencimento,
        Integer status
) { }