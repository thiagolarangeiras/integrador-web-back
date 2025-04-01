package com.github.integrador.PedidoSaidaParcela;

import java.util.Date;

public record PedidoSaidaParcelaPostDto(
        Integer id,
        Integer idPedidoSaida,
        Double valor,
        Date dataVencimento,
        Integer status
) { }