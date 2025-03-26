package com.github.integrador.dtos;

import lombok.Builder;

@Builder
public record FornecedorGetDto(
    Integer id,
    String nome,
    String cpfCnpj,
    String endereco,
    String telefone,
    String email
) { }