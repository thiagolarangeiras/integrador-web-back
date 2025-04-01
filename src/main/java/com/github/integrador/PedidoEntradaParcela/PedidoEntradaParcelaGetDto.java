package com.github.integrador.PedidoEntradaParcela;

import com.github.integrador.enums.StatusParcela;

import java.util.Date;

public record PedidoEntradaParcelaGetDto(
        Integer id,
        Integer idPedidoEntrada,
        Double valor,
        Date dataVencimento,
        StatusParcela status
) { }