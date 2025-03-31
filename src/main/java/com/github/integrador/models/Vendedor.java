package com.github.integrador.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.github.integrador.dtos.UserGetDto;
import com.github.integrador.dtos.UserPostDto;
import com.github.integrador.dtos.VendedorGetDto;
import com.github.integrador.dtos.VendedorPostDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashSet;
import java.util.List;

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
    public static VendedorGetDto mapToDto(Vendedor vend) {
        return new VendedorGetDto(
            vend.getId(),
            vend.getNome(),
            vend.getDescricao(),
            vend.getCpf(),
            vend.getTelefone(),
            vend.getEmail()
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