package com.github.integrador.PedidoSaida;

import com.github.integrador.Cliente.ClienteGetDto;
import com.github.integrador.Vendedor.VendedorGetDto;
import com.github.integrador.enums.StatusEntrega;
import com.github.integrador.enums.StatusPagamento;
import com.github.integrador.enums.StatusPedido;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoSaidaGetDto{
        Integer id;
        Integer idCliente;
        Integer idVendedor;
        Date dataCriacao;
        Date dataVigencia;
        Date dataEntregaPrevista;
        Date dataEntregaReal;
        StatusPedido status;
        StatusEntrega statusEntrega;
        StatusPagamento statusPagamento;
        Double valorTotal;
        Double valorFrete;
        ClienteGetDto cliente;
        VendedorGetDto vendedor;
}