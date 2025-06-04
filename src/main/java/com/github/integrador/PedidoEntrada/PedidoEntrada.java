package com.github.integrador.PedidoEntrada;

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
    private StatusPedido status;
    private StatusEntrega statusEntrega;
    private StatusPagamento statusPagamento;
    private Double valorTotal;
    private Double valorFrete;

    //@ManyToOne
    //@JoinColumn(name = "id_fornecedor")
    //private Fornecedor fornecedor;

    //@OneToMany(mappedBy = "pedido")
    //private List<PedidoEntradaProduto> produtos;

    //@OneToMany(mappedBy = "pedido")
    //private List<PedidoEntradaParcela> parcelas;

    public static PedidoEntradaGetDto mapToDto(PedidoEntrada obj) {
        return new PedidoEntradaGetDto(
            obj.getId(),
            obj.getIdFornecedor(),
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
            null,
            null
        );
    }

    public static PedidoEntrada mapToObj(PedidoEntradaPostDto dto) {
        PedidoEntrada obj = new PedidoEntrada();
        obj.idFornecedor        = dto.idFornecedor();
        obj.dataCriacao         = dto.dataCriacao();
        obj.dataVigencia        = dto.dataVigencia();
        obj.dataEntregaPrevista = dto.dataEntregaPrevista();
        obj.dataEntregaReal     = dto.dataEntregaReal();
        obj.status              = dto.status();
        obj.statusEntrega       = dto.statusEntrega();
        obj.statusPagamento     = dto.statusPagamento();
        obj.valorTotal          = dto.valorTotal();
        obj.valorFrete          = dto.valorFrete();
        return obj;
    }
}

