package com.github.integrador.Vendedor;

public record VendedorPostDto(
    String nome,
    String descricao,
    String cpf,
    String telefone,
    String email
) { }