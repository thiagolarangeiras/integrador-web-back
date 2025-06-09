package com.github.integrador.DadosEmpresa;

import com.github.integrador.enums.EstadosBr;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DadosEmpresaPostDto {
    private String nome;
    private String cnpj;
    private String ie;
    private String email;
    private String telefone;

    private String cep;
    private String rua;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private EstadosBr estado;
}