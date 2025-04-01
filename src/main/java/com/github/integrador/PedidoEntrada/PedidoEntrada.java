package com.github.integrador.PedidoEntrada;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "pedido_entrada")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PedidoEntrada {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer idFornecedor;

    private Date dataCriacao;
    private Date dataVigencia;
    private Date dataEntregaPrevista;
    private Date dataEntregaReal;
    private Integer statusEntrega;
    private Integer statusPagamento;
    private Double valorTotal;
    private Double valorFrete;

    //@ManyToOne
    //@JoinColumn(name = "id_fornecedor")
    //private Fornecedor fornecedor;

    //@OneToMany(mappedBy = "pedido")
    //private List<PedidoEntradaProduto> produtos;

    //@OneToMany(mappedBy = "pedido")
    //private List<PedidoEntradaParcela> parcelas;

    //Mappers
    public static PedidoEntradaGetDto mapToDto(PedidoEntrada obj) {
        return new PedidoEntradaGetDto(
                obj.getId(),
                obj.getIdFornecedor(),
                obj.getDataCriacao(),
                obj.getDataVigencia(),
                obj.getDataEntregaPrevista(),
                obj.getDataEntregaReal(),
                obj.getStatusEntrega(),
                obj.getStatusPagamento(),
                obj.getValorTotal(),
                obj.getValorFrete()
        );
    }

    public static PedidoEntrada mapToObj(PedidoEntradaPostDto dto) {
        return PedidoEntrada.builder()
                .idFornecedor(dto.idFornecedor())
                .dataCriacao(dto.dataCriacao())
                .dataVigencia(dto.dataVigencia())
                .dataEntregaPrevista(dto.dataEntregaPrevista())
                .dataEntregaReal(dto.dataEntregaReal())
                .statusEntrega(dto.statusEntrega())
                .statusPagamento(dto.statusPagamento())
                .valorTotal(dto.valorTotal())
                .valorFrete(dto.valorFrete())
                .build();
    }
}

