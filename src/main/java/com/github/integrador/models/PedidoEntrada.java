package com.github.integrador.models;

import com.github.integrador.dtos.MarcaGetDto;
import com.github.integrador.dtos.MarcaPostDto;
import com.github.integrador.dtos.PedidoEntradaGetDto;
import com.github.integrador.dtos.PedidoEntradaPostDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;



//    private Integer id;
//    private Integer idCliente;
//    private Integer idVendedor;
//    private List<Produto> produtos;
//    private Integer quantidade;
//    private Date dataEntregaPrevista;
//   private Integer status;


@Entity
@Table(name = "pedido_entrada")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PedidoEntrada {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer idFornecedor;
    private Double valorTotal;
    private Double valorFrete;

    @ManyToOne
    @JoinColumn(name = "id_fornecedor")
    private Fornecedor fornecedor;

    @OneToMany(mappedBy = "pedido_entrada_produto")
    private List<PedidoEntradaProduto> produtos;

    //Mappers
    public static PedidoEntradaGetDto mapToDto(PedidoEntrada obj) {
        return new PedidoEntradaGetDto(
                obj.getId(),
                obj.getIdFornecedor(),
                obj.getValorTotal(),
                obj.getValorFrete()
        );
    }

    public static PedidoEntrada mapToObj(PedidoEntradaPostDto dto) {
        return PedidoEntrada.builder()
                .idFornecedor(dto.idFornecedor())
                .valorTotal(dto.valorTotal())
                .valorFrete(dto.valorFrete())
                .build();
    }
}
