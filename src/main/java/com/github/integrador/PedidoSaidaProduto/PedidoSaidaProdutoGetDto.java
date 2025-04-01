package com.github.integrador.PedidoSaidaProduto;

public record PedidoSaidaProdutoGetDto(
        Integer id,
        Integer idPedidoSaida,
        Integer idProduto,
        Integer qtde,
        Double valorUnitario,
        Double valorTotal
) { }