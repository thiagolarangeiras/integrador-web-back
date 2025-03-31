package com.github.integrador.models;

import com.github.integrador.dtos.PedidoSaidaParcelaGetDto;
import com.github.integrador.dtos.PedidoSaidaParcelaPostDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pedido_saida_parcela")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PedidoSaidaParcela {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer idPedidoSaida;

    private Double valor;
    private Data dataVencimento;
    private Integer status;

    //@ManyToOne
    //@JoinColumn(name = "id_pedido_saida")
    //private PedidoSaida pedido;

    //Mappers
    public static PedidoSaidaParcelaGetDto mapToDto(PedidoSaidaParcela obj) {
        return new PedidoSaidaParcelaGetDto(
                obj.getId(),
                obj.getIdPedidoSaida(),
                obj.getValor(),
                obj.getDataVencimento(),
                obj.getStatus()
        );
    }

    public static PedidoSaidaParcela mapToObj(PedidoSaidaParcelaPostDto dto) {
        return PedidoSaidaParcela.builder()
                .idPedidoSaida(dto.idPedidoSaida())
                .valor(dto.valor())
                .dataVencimento(dto.dataVencimento())
                .status(dto.status())
                .build();
    }
}