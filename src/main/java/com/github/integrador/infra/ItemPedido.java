package com.github.integrador.infra;

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
