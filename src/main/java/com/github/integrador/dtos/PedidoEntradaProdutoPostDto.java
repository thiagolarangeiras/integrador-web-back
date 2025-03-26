package com.github.integrador.dtos;

public record PedidoEntradaProdutoPostDto(
        Integer id,
        Integer idPedidoEntrada,
        Integer idProduto,
        Integer qtde,
        Double valorUnitario,
        Double valorTotal
) { }