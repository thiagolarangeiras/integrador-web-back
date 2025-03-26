package com.github.integrador.dtos;

import lombok.Builder;

@Builder
public record ClienteGetDto(
    Integer id,
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