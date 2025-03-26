package com.github.integrador.dtos;

public record VendedorPostDto(
    String nome,
    String descricao,
    String cpf,
    String telefone,
    String email
) { }