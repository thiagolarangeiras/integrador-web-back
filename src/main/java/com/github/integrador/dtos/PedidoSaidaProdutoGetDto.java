package com.github.integrador.dtos;

public record PedidoSaidaProdutoGetDto(
        Integer id,
        Integer idPedidoSaida,
        Integer idProduto,
        Integer qtde,
        Double valorUnitario,
        Double valorTotal
) { }