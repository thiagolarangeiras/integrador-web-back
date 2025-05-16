package com.github.integrador.Infra;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class PdfPedidoItens {
    String codigo;
    String descricao;
    String situacaoTributaria;
    String quantidade;
    String valorUnitario;
    String valorTotal;
}
