package com.github.integrador.PedidoSaida.pdf;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class PdfPedidoItens {
    public String codigo;
    public String descricao;
    public String situacaoTributaria;
    public String quantidade;
    public String valorUnitario;
    public String valorTotal;
}
