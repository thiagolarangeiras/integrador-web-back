package com.github.integrador.dtos;

public record PedidoEntradaProdutoGetDto(
    Integer id,
    Integer idPedidoEntrada,
    Integer idProduto,
    Integer qtde,
    Double valorUnitario,
    Double valorTotal
) { }