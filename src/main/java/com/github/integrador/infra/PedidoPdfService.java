package com.github.integrador.Infra;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;

import java.util.List;

public class PedidoPdfService {

    public void startDocument(Document document, PdfPedidoDados dados){
        try {
            List<PdfPedidoItens> itens = dados.itens;

            // Fonte personalizada
            Font fontTitulo = new Font(Font.HELVETICA, 16, Font.BOLD);
            Font fontNormal = new Font(Font.HELVETICA, 10, Font.NORMAL);

            // Título
            Paragraph titulo = new Paragraph(dados.nomeEmpresa, fontTitulo);
            titulo.setAlignment(Element.ALIGN_CENTER);
            document.add(titulo);

            document.add(new Paragraph(dados.cnpjEmpresa, fontNormal));
            document.add(new Paragraph(dados.emailEmpresa, fontNormal));
            document.add(new Paragraph(dados.telefoneEmpresa, fontNormal));

            // Dados do cliente
            PdfPTable tabelaCliente = new PdfPTable(2);
            tabelaCliente.setWidthPercentage(100);
            tabelaCliente.addCell(dados.nomeCliente);
            tabelaCliente.addCell(dados.cnpjCliente);
            tabelaCliente.addCell(dados.enderecoCliente);
            tabelaCliente.addCell(dados.emailCliente);
            tabelaCliente.addCell(dados.telefoneCliente);
            tabelaCliente.addCell(dados.dataPedido);
            document.add(tabelaCliente);

            document.add(new Paragraph("\nItens:", fontTitulo));

            // Tabela de itens
            PdfPTable tabelaItens = new PdfPTable(7);
            tabelaItens.setWidthPercentage(100);
            tabelaItens.addCell("#");
            tabelaItens.addCell("Código");
            tabelaItens.addCell("Descrição");
            tabelaItens.addCell("Sit. trib.");
            tabelaItens.addCell("Qtd");
            tabelaItens.addCell("Valor Unid");
            tabelaItens.addCell("Total");

            Integer i = 1;
            for(PdfPedidoItens item : itens) {
                tabelaItens.addCell(i.toString());
                tabelaItens.addCell(item.codigo);
                tabelaItens.addCell(item.descricao);
                tabelaItens.addCell(item.situacaoTributaria);
                tabelaItens.addCell(item.quantidade);
                tabelaItens.addCell(item.valorUnitario);
                tabelaItens.addCell(item.valorTotal);
                i++;
            }
            document.add(tabelaItens);

            document.add(new Paragraph(dados.condicaoPagamento, fontNormal));
            document.add(new Paragraph(dados.valorTotal, fontNormal));

            // Assinaturas
            PdfPTable tabelaAssinatura = new PdfPTable(2);
            tabelaAssinatura.setWidthPercentage(100);
            tabelaAssinatura.addCell(dados.nomeVendedor);
            tabelaAssinatura.addCell(dados.nomeCliente);

            document.add(tabelaAssinatura);

            // document.add(new Paragraph("\n\nAutoriza o Orçamento para Execução e Faturamento", fontNormal));
            // document.add(new Paragraph("______________________________"));
            // document.add(new Paragraph("Assinatura"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}