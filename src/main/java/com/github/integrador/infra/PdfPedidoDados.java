package com.github.integrador.Infra;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

//@AllArgsConstructor
@NoArgsConstructor
public class PdfPedidoDados {
    String nomeEmpresa = "EMPRESA LTDA";
    String cnpjEmpresa = "CNPJ: 99.999.999/0001-99 IE: 123456789";
    String emailEmpresa = "email@email.com.br | www.email.com.br";
    String telefoneEmpresa = "Telefone: (48) 9999-9999\n\n";
    String nomeClienteEmpresa = "Cliente: Fulano LTDA";
    String cnpjCliente = "CPF: 999.999.999-99";
    String enderecoCliente = "Endereço: Rua ABC, 99, Centro - Criciúma/SC";
    String emailCliente = "E-mail: cliente@email.com";
    String telefoneCliente = "Telefone: (48) 9 9999-9999";
    String dataPedido = "Data: 05/02/2025 - 13:00";
    String nomeVendedor = "Vendador: Vendedor1";
    String nomeCliente = "Cliente: Fulao Fulano Fulano";

    String condicaoPagamento = "\nCondição de Pagamento: Carteira 01X";
    String valorTotal = "Total: R$ 350,00\n\n";

    List<PdfPedidoItens> itens;

    PdfPedidoDados(
        String nomeEmpresa,
        String cnpjEmpresa,
        String emailEmpresa,
        String telefoneEmpresa,
        String nomeClienteEmpresa,
        String cnpjCliente,
        String enderecoCliente,
        String emailCliente,
        String telefoneCliente,
        String dataPedido,
        String nomeVendedor,
        String nomeCliente,
        String condicaoPagamento,
        String valorTotal,
        List<PdfPedidoItens> itens
    ){
        this.nomeEmpresa =      nomeEmpresa;
        this.cnpjEmpresa =      cnpjEmpresa;
        this.emailEmpresa =     emailEmpresa;
        this.telefoneEmpresa =  telefoneEmpresa;
        this.nomeClienteEmpresa = nomeClienteEmpresa;
        this.cnpjCliente =      cnpjCliente;
        this.enderecoCliente =  enderecoCliente;
        this.emailCliente =     emailCliente;
        this.telefoneCliente =  telefoneCliente;
        this.dataPedido =       dataPedido;
        this.nomeVendedor =     nomeVendedor;
        this.nomeCliente =      nomeCliente;
        this.condicaoPagamento = condicaoPagamento;
        this.valorTotal =       valorTotal;
        
    }
}
