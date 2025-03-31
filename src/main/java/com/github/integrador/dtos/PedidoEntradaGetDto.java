package com.github.integrador.dtos;

public record PedidoEntradaGetDto(
    Integer id,
    Integer idFornecedor,
    Double valorTotal,
    Double valorFrete
){ }