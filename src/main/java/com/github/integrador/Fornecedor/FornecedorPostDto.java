package com.github.integrador.Fornecedor;

import lombok.Builder;

@Builder
public record FornecedorPostDto(
    String nome,
    String cpfCnpj,
    String endereco,
    String telefone,
    String email
) { }