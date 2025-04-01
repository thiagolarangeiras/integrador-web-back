package com.github.integrador.PedidoSaidaParcela;

import com.github.integrador.enums.StatusParcela;

import java.util.Date;

public record PedidoSaidaParcelaPostDto(
        Integer id,
        Integer idPedidoSaida,
        Double valor,
        Date dataVencimento,
        StatusParcela status
) { }