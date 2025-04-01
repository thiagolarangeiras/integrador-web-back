package com.github.integrador.Fornecedor;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "fornecedor")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Fornecedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome; //nome da empresa
    private String cpfCnpj;
    private String endereco;
    private String telefone;
    private String email;

    //@OneToMany(mappedBy = "fornecedor")
    //private List<Produto> produtosFornecidos;

    //Mappers
    public static FornecedorGetDto mapToDto(Fornecedor obj) {
        return new FornecedorGetDto(
                obj.getId(),
                obj.getNome(),
                obj.getCpfCnpj(),
                obj.getEndereco(),
                obj.getTelefone(),
                obj.getEmail()
        );
    }

    public static Fornecedor mapToObj(FornecedorPostDto dto) {
        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setNome(dto.nome());
        fornecedor.setCpfCnpj(dto.cpfCnpj());
        fornecedor.setEndereco(dto.endereco());
        fornecedor.setTelefone(dto.telefone());
        fornecedor.setEmail(dto.email());
        return fornecedor;
    }
}