package com.github.integrador.PedidoEntradaProduto;

import com.github.integrador.Produto.ProdutoGetDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoEntradaProdutoGetDto {
    Integer id;
    Integer idPedidoEntrada;
    Integer idProduto;
    Integer qtde;
    Double valorUnitario;
    Double valorTotal;
    ProdutoGetDto produto;
}