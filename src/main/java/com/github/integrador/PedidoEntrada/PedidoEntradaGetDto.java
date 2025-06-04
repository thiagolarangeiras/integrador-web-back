package com.github.integrador.PedidoEntrada;

import com.github.integrador.Fornecedor.FornecedorGetDto;
import com.github.integrador.PedidoEntradaParcela.PedidoEntradaParcelaGetDto;
import com.github.integrador.PedidoEntradaProduto.PedidoEntradaProdutoGetDto;
import com.github.integrador.enums.StatusEntrega;
import com.github.integrador.enums.StatusPagamento;
import com.github.integrador.enums.StatusPedido;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoEntradaGetDto{
    public Integer id;
    public Integer idFornecedor;
    public Date dataCriacao;
    public Date dataVigencia;
    public Date dataEntregaPrevista;
    public Date dataEntregaReal;
    public StatusPedido status;
    public StatusEntrega statusEntrega;
    public StatusPagamento statusPagamento;
    public Double valorTotal;
    public Double valorFrete;

    public FornecedorGetDto fornecedor;
    public List<PedidoEntradaProdutoGetDto> pedidoProdutos;
    public List<PedidoEntradaParcelaGetDto> pedidoParcelas;
}