package com.github.integrador.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "fornecedores")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Fornecedor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String nome; //nome da empresa
    private String cnpj;
    private String endereco;
    private String telefone;
    private String email;

    // tem que fazer a ligação many to one ou many
    // to many, depende se o produtos vai poder ser fornecido pro mais de um (eu acho que não)
    private List<Produto> produtosFornecidos;
}