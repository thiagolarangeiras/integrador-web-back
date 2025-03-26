package com.github.integrador.dtos;

public record ClientePostDto(
    Integer idVendedor,
    String nomePessoa,
    String nomeEmpresa,
    String cpf,
    String cnpj,
    String endereco,
    String telefone,
    String email,
    String descricao
) { }