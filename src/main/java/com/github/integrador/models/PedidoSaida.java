package com.github.integrador.models;

import com.github.integrador.dtos.PedidoSaidaGetDto;
import com.github.integrador.dtos.PedidoSaidaPostDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "pedido_saida")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PedidoSaida {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer idCliente;
    private Integer idVendedor;
    private Date dataCriacao;
    private Date dataVigencia;
    private Date dataEntregaPrevista;
    private Date dataEntregaReal;
    private Integer statusEntrega;
    private Integer statusPagamento;

    private Double valorTotal;
    private Double valorFrete;

    //@ManyToOne
    //@JoinColumn(name = "id_vendedor")
    //private Vendedor vendedor;

    //@OneToMany(mappedBy = "pedido")
    //private List<PedidoSaidaProduto> produtos;

    //@OneToMany(mappedBy = "pedido")
    //private List<PedidoSaidaParcela> parcelas;

    //Mappers
    public static PedidoSaidaGetDto mapToDto(PedidoSaida obj) {
        return new PedidoSaidaGetDto(
                obj.getId(),
                obj.getIdCliente(),
                obj.getIdVendedor(),
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

    public static PedidoSaida mapToObj(PedidoSaidaPostDto dto) {
        return PedidoSaida.builder()
                .idCliente(dto.idCliente())
                .idVendedor(dto.idVendedor())
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