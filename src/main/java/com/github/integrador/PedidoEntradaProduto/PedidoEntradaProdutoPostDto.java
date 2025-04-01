package com.github.integrador.PedidoEntradaProduto;

public record PedidoEntradaProdutoPostDto(
        Integer idPedidoEntrada,
        Integer idProduto,
        Integer qtde,
        Double valorUnitario,
        Double valorTotal
) { }