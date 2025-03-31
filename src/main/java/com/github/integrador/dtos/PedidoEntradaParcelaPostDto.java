package com.github.integrador.dtos;

import lombok.Data;

public record PedidoEntradaParcelaPostDto(
        Integer idPedidoEntrada,
        Double valor,
        Data dataVencimento,
        Integer status
) { }