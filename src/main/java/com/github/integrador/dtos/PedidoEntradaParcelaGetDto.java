package com.github.integrador.dtos;

import lombok.Data;

public record PedidoEntradaParcelaGetDto(
        Integer id,
        Integer idPedidoEntrada,
        Double valor,
        Data dataVencimento,
        Integer status
) { }