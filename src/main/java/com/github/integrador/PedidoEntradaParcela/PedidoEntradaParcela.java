package com.github.integrador.PedidoEntradaParcela;

import com.github.integrador.enums.StatusParcela;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "pedido_entrada_parcela")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PedidoEntradaParcela {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer idPedidoEntrada;

    private Double valor;
    private Date dataVencimento;
    private StatusParcela status;

    //@ManyToOne
    //@JoinColumn(name = "id_pedido_entrada")
    //private PedidoEntrada pedido;

    //Mappers
    public static PedidoEntradaParcelaGetDto mapToDto(PedidoEntradaParcela obj) {
        return new PedidoEntradaParcelaGetDto(
                obj.getId(),
                obj.getIdPedidoEntrada(),
                obj.getValor(),
                obj.getDataVencimento(),
                obj.getStatus()
        );
    }

    public static PedidoEntradaParcela mapToObj(PedidoEntradaParcelaPostDto dto) {
        return PedidoEntradaParcela.builder()
                .idPedidoEntrada(dto.idPedidoEntrada())
                .valor(dto.valor())
                .dataVencimento(dto.dataVencimento())
                .status(dto.status())
                .build();
    }
}