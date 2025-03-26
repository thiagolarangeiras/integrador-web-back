package com.github.integrador.dtos;

import lombok.Builder;

@Builder
public record FornecedorPostDto(
    String nome,
    String cpfCnpj,
    String endereco,
    String telefone,
    String email
) { }