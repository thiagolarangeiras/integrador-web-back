package com.github.integrador.PedidoSaida;

import com.github.integrador.enums.StatusEntrega;
import com.github.integrador.enums.StatusPagamento;
import com.github.integrador.enums.StatusPedido;
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
    private StatusPedido status;
    private StatusEntrega statusEntrega;
    private StatusPagamento statusPagamento;

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
                obj.getStatus(),
                obj.getStatusEntrega(),
                obj.getStatusPagamento(),
                obj.getValorTotal(),
                obj.getValorFrete(),
                null,
                null
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
                .status(dto.status())
                .statusEntrega(dto.statusEntrega())
                .statusPagamento(dto.statusPagamento())
                .valorTotal(dto.valorTotal())
                .valorFrete(dto.valorFrete())
                .build();
    }
}