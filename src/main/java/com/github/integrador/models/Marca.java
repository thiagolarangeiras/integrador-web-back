package com.github.integrador.models;


import com.github.integrador.dtos.MarcaGetDto;
import com.github.integrador.dtos.MarcaPostDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "marca")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Marca {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;

    //@OneToMany(mappedBy = "marca")
    //private List<Produto> produtos;

    //Mappers
    public static MarcaGetDto mapToDto(Marca obj) {
        return new MarcaGetDto(
            obj.getId(),
            obj.getNome()
        );
    }

    public static Marca mapToObj(MarcaPostDto dto) {
        return Marca.builder()
            .nome(dto.nome())
            .build();
    }
}