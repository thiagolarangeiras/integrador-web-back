package com.github.integrador.dtos;

public record PedidoEntradaPostDto(
    Integer idFornecedor,
    Double valorTotal,
    Double valorFrete
) { }