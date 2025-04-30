package com.github.integrador.Produto;

import com.github.integrador.Fornecedor.FornecedorGetDto;
import com.github.integrador.Marca.MarcaGetDto;
import lombok.Builder;

@Builder
public record ProdutoGetDto(
	Integer id,
    Integer idFornecedor,
	Integer idMarca,
    MarcaGetDto marca,
	FornecedorGetDto fornecedor,
    String nome,
    String descricao,
    Double valorCompra,
    Double valorVenda,
    Integer categoria,
    Integer qtEstoque
) {}