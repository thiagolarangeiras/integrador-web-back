package com.github.integrador.PedidoEntradaProduto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pedido_entrada_produto")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PedidoEntradaProduto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer idPedidoEntrada;
    private Integer idProduto;

    private Integer qtde;
    private Double valorUnitario;
    private Double valorTotal;

    //@ManyToOne
    //@JoinColumn(name = "id_pedido_entrada")
    //private PedidoEntradaProduto pedido;

    //@ManyToOne
    //@JoinColumn(name = "id_produto")
    //private Produto produto;

    //Mappers
    public static PedidoEntradaProdutoGetDto mapToDto(PedidoEntradaProduto obj) {
        return new PedidoEntradaProdutoGetDto(
            obj.getId(),
            obj.getIdPedidoEntrada(),
            obj.getIdProduto(),
            obj.getQtde(),
            obj.getValorUnitario(),
            obj.getValorTotal(),
                null
        );
    }

    public static PedidoEntradaProduto mapToObj(PedidoEntradaProdutoPostDto dto) {
        return PedidoEntradaProduto.builder()
            .idPedidoEntrada(dto.idPedidoEntrada())
            .idProduto(dto.idProduto())
            .qtde(dto.qtde())
            .valorUnitario(dto.valorUnitario())
            .valorTotal(dto.valorTotal())
            .build();
    }
}