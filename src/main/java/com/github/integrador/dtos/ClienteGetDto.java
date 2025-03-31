package com.github.integrador.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.integrador.models.Vendedor;
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


)

{

}