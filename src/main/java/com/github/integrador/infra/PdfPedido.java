package com.github.integrador.Infra;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@AllArgsConstructor
@NoArgsConstructor
class ItemPedido {
    String nomeProduto;
    int quantidade;
    double preco;

    double getSubtotal() {
        return quantidade * preco;
    }
}

@RestController
@RequestMapping("/pdf")
public class PdfPedido {

    void startDocument(Document document){
        Font tituloFont = new Font(Font.HELVETICA, 16, Font.BOLD);
        Font normalFont = new Font(Font.HELVETICA, 12, Font.NORMAL);

        Paragraph empresa = new Paragraph("Minha Empresa LTDA\nCNPJ: 00.000.000/0001-00\nRua Exemplo, 123 - Cidade\n", normalFont);
        empresa.setAlignment(Element.ALIGN_CENTER);
        document.add(empresa);
        document.add(new Paragraph("\n"));

        // Dados do pedido
        document.add(new Paragraph("Pedido Nº: 12345", tituloFont));
        document.add(new Paragraph("Data: 11/04/2025\nCliente: João da Silva\nCPF: 123.456.789-00\n", normalFont));
        document.add(new Paragraph("\n"));

        // Tabela de itens
        PdfPTable tabela = new PdfPTable(4);
        tabela.setWidthPercentage(100);
        tabela.setWidths(new float[]{4, 1, 2, 2});
        tabela.addCell("Produto");
        tabela.addCell("Qtd");
        tabela.addCell("Preço");
        tabela.addCell("Subtotal");

        java.util.List<ItemPedido> itens = Arrays.asList(
                new ItemPedido("Camiseta Azul aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", 2, 50.0),
                new ItemPedido("Tênis XYZ", 1, 200.0)
        );

        double total = 0;
        for (ItemPedido item : itens) {
            tabela.addCell(item.nomeProduto);
            tabela.addCell(String.valueOf(item.quantidade));
            tabela.addCell(String.format("R$ %.2f", item.preco));
            tabela.addCell(String.format("R$ %.2f", item.getSubtotal()));
            total += item.getSubtotal();
        }

        document.add(tabela);
        document.add(new Paragraph("\n"));

        // Total
        Paragraph totalP = new Paragraph("Total: R$ " + String.format("%.2f", total), tituloFont);
        totalP.setAlignment(Element.ALIGN_RIGHT);
        document.add(totalP);

        // Pagamento e observações
        document.add(new Paragraph("Forma de pagamento: Cartão de crédito", normalFont));
        document.add(new Paragraph("Observações: Entregar até 15/04/2025", normalFont));
    }

    @GetMapping(value = "/pedido", produces = MediaType.APPLICATION_PDF_VALUE)
    public void gerarPdf(HttpServletResponse response) {
        try {
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=\"arquivo.pdf\"");
            Document document = new Document();
            PdfWriter.getInstance(document, response.getOutputStream());
            document.open();
            startDocument(document);
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}