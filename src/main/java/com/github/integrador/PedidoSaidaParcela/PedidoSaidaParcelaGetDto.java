package com.github.integrador.PedidoSaidaParcela;

import lombok.Data;

public record PedidoSaidaParcelaGetDto(
        Integer id,
        Integer idPedidoSaida,
        Double valor,
        Data dataVencimento,
        Integer status
) { }