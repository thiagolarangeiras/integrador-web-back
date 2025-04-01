package com.github.integrador.PedidoEntradaProduto;

public record PedidoEntradaProdutoGetDto(
    Integer id,
    Integer idPedidoEntrada,
    Integer idProduto,
    Integer qtde,
    Double valorUnitario,
    Double valorTotal
) { }