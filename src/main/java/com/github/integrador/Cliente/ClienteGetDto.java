package com.github.integrador.Cliente;

import com.github.integrador.Vendedor.VendedorGetDto;
import com.github.integrador.enums.EstadosBr;
import com.github.integrador.enums.TipoCliente;
import lombok.Builder;

@Builder
public record ClienteGetDto (
    Integer id,
    Integer idVendedor,
    VendedorGetDto vendedor,
    String nome,
    String nomePessoa,
    String nomeEmpresa,
    String nomeFantasia,
    String descricao,
    TipoCliente tipo,
    String cpfCnpj,
    String telefone,
    String email,
    String cep,
    String rua,
    String numero,
    String complemento,
    String bairro,
    String cidade,
    EstadosBr estado
) { }