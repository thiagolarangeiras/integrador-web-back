package com.github.integrador.Vendedor;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "vendedor")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Vendedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String descricao;
    private String cpf;
    private String telefone;
    private String email;

    //@OneToMany(mappedBy = "vendedor")
    //@JsonIgnore
    //private List<Cliente> clientes;

    //Mappers
    public static VendedorGetDto mapToDto(Vendedor obj) {
        if(obj == null) return null;
        return new VendedorGetDto(
                obj.getId(),
                obj.getNome(),
                obj.getDescricao(),
                obj.getCpf(),
                obj.getTelefone(),
                obj.getEmail()
        );
    }

    public static Vendedor mapToObj(VendedorPostDto dto) {
        return Vendedor.builder()
            .nome(dto.nome())
            .descricao(dto.descricao())
            .cpf(dto.cpf())
            .telefone(dto.telefone())
            .email(dto.email())
            .build();
    }
}