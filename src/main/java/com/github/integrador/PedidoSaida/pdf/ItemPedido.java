package com.github.integrador.PedidoSaida.pdf;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class ItemPedido {
    String nomeProduto;
    int quantidade;
    double preco;

    double getSubtotal() {
        return quantidade * preco;
    }
}
