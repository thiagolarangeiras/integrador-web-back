package com.github.integrador.PedidoEntradaParcela;

import com.github.integrador.enums.StatusParcela;

import java.util.Date;

public record PedidoEntradaParcelaPostDto(
        Integer idPedidoEntrada,
        Double valor,
        Date dataVencimento,
        StatusParcela status
) { }