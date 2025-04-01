package com.github.integrador.Produto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "produto")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer idFornecedor;
    private Integer idMarca;

    private String nome;
    private String descricao;
    private Double valorCompra;
    private Double valorVenda;
    private Integer categoria;
    private Integer qtEstoque;

    //@ManyToOne
    //@JoinColumn(name = "id_fornecedor")
    //private Fornecedor fornecedor;

    //@ManyToOne
    //@JoinColumn(name = "id_marca")
    //private Marca marca;

    //Mappers
    public static ProdutoGetDto mapToDto(Produto obj) {
        return new ProdutoGetDto(
            obj.getId(),
            obj.getIdFornecedor(),
            obj.getIdMarca(),
            obj.getNome(),
            obj.getDescricao(),
            obj.getValorCompra(),
            obj.getValorVenda(),
            obj.getCategoria(),
            obj.getQtEstoque()
        );
    }

    public static Produto mapToObj(ProdutoPostDto dto) {
        return Produto.builder()
            .idFornecedor(dto.idFornecedor())
            .idMarca(dto.idMarca())
            .nome(dto.nome())
            .descricao(dto.descricao())
            .valorCompra(dto.valorCompra())
            .valorVenda(dto.valorVenda())
            .categoria(dto.categoria())
            .qtEstoque(dto.qtEstoque())
            .build();
    }
}