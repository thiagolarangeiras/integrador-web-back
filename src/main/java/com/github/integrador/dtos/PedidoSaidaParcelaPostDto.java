package com.github.integrador.dtos;

import lombok.Data;

public record PedidoSaidaParcelaPostDto(
        Integer id,
        Integer idPedidoSaida,
        Double valor,
        Data dataVencimento,
        Integer status
) { }