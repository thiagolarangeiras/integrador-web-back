package com.github.integrador.models;

import com.github.integrador.dtos.PedidoSaidaProdutoGetDto;
import com.github.integrador.dtos.PedidoSaidaProdutoPostDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pedido_saida_produto")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PedidoSaidaProduto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer idPedidoSaida;
    private Integer idProduto;

    private Integer qtde;
    private Double valorUnitario;
    private Double valorTotal;

    //@ManyToOne
    //@JoinColumn(name = "id_pedido_saida")
    //private PedidoSaidaProduto pedido;

    //@ManyToOne
    //@JoinColumn(name = "id_produto")
    //private Produto produto;

    //Mappers
    public static PedidoSaidaProdutoGetDto mapToDto(PedidoSaidaProduto obj) {
        return new PedidoSaidaProdutoGetDto(
                obj.getId(),
                obj.getIdPedidoSaida(),
                obj.getIdProduto(),
                obj.getQtde(),
                obj.getValorUnitario(),
                obj.getValorTotal()
        );
    }

    public static PedidoSaidaProduto mapToObj(PedidoSaidaProdutoPostDto dto) {
        return PedidoSaidaProduto.builder()
                .idPedidoSaida(dto.idPedidoSaida())
                .idProduto(dto.idProduto())
                .qtde(dto.qtde())
                .valorUnitario(dto.valorUnitario())
                .valorTotal(dto.valorTotal())
                .build();
    }
}