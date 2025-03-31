package com.github.integrador.dtos;

public record PedidoSaidaProdutoPostDto(
        Integer idPedidoSaida,
        Integer idProduto,
        Integer qtde,
        Double valorUnitario,
        Double valorTotal
) { }