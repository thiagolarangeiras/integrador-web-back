package com.github.integrador.PedidoSaidaProduto;

import com.github.integrador.Produto.ProdutoGetDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoSaidaProdutoGetDto {
        Integer id;
        Integer idPedidoSaida;
        Integer idProduto;
        Integer qtde;
        Double valorUnitario;
        Double valorTotal;
        ProdutoGetDto produto;
}