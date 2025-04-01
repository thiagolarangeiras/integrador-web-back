package com.github.integrador.Vendedor;

import lombok.Builder;

@Builder
public record VendedorGetDto(
    Integer id,
    String nome,
    String descricao,
    String cpf,
    String telefone,
    String email
) { }
