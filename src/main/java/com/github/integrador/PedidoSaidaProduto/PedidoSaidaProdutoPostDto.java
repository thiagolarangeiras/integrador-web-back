package com.github.integrador.PedidoSaidaProduto;

public record PedidoSaidaProdutoPostDto(
        Integer idPedidoSaida,
        Integer idProduto,
        Integer qtde,
        Double valorUnitario,
        Double valorTotal
) { }