package com.github.integrador.Produto;

public record ProdutoPostDto(
    Integer idFornecedor,
    Integer idMarca,
    String nome,
    String descricao,
    Double valorCompra,
    Double valorVenda,
    Integer categoria,
    Integer qtEstoque
) {}