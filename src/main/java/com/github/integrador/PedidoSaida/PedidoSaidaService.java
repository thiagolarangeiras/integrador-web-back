package com.github.integrador.PedidoSaida;

import com.github.integrador.Cliente.ClienteService;
import com.github.integrador.DadosEmpresa.DadosEmpresaGetDto;
import com.github.integrador.DadosEmpresa.DadosEmpresaService;
import com.github.integrador.PedidoSaida.pdf.PdfPedidoItens;
import com.github.integrador.PedidoSaidaProduto.PedidoSaidaProdutoGetDto;
import com.github.integrador.PedidoSaidaProduto.PedidoSaidaProdutoService;
import com.github.integrador.Vendedor.VendedorService;
import com.github.integrador.PedidoSaida.pdf.PdfPedidoDados;
import com.github.integrador.PedidoSaidaParcela.PedidoSaidaParcela;
import com.github.integrador.PedidoSaidaParcela.PedidoSaidaParcelaRepo;
import com.github.integrador.enums.TipoCliente;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class PedidoSaidaService {
    @Autowired private PedidoSaidaRepo repo;
    @Autowired private PedidoSaidaParcelaRepo parcelaRepo;
    @Autowired private PedidoSaidaProdutoService produtoService;
    @Autowired private ClienteService clienteService;
    @Autowired private VendedorService vendedorService;
    @Autowired private DadosEmpresaService dadosEmpresaService;

    public List<PedidoSaidaGetDto> getAll (int page, int count) {
        Pageable pageable = PageRequest.of(page, count);
        List<PedidoSaidaGetDto> dtos = repo.findAll(pageable)
                .stream()
                .map(PedidoSaida::mapToDto)
                .toList();
        for (PedidoSaidaGetDto dto : dtos){
            dto.cliente = clienteService.getOne(dto.getIdCliente());
            dto.vendedor = vendedorService.getOne(dto.getIdVendedor());
        }
        return dtos;
    }

    public PedidoSaidaGetDto getOne (Integer id) {
        if(id == null) return null;
        PedidoSaida obj = repo.findById(id).orElse(null);
        PedidoSaidaGetDto dto = PedidoSaida.mapToDto(obj);
        dto.cliente = clienteService.getOne(dto.getIdCliente());
        dto.vendedor = vendedorService.getOne(dto.getIdVendedor());
        return dto;
    }

    public PedidoSaidaGetDto post(PedidoSaidaPostDto dto) {
        PedidoSaida obj = PedidoSaida.mapToObj(dto);
        obj = repo.save(obj);
        return PedidoSaida.mapToDto(obj);
    }

    public PedidoSaidaGetDto patch(Integer id, PedidoSaidaPostDto dto) {
        PedidoSaida obj = repo.findById(id).orElseThrow();
        obj = PedidoSaida.mapToObj(dto);
        obj.setId(id);
        obj = repo.save(obj);
        return PedidoSaida.mapToDto(obj);
    }

    public void delete(Integer id) {
        repo.deleteById(id);
    }

    public PdfPedidoDados getDadosPdf(Integer id){
        PedidoSaidaGetDto pedido = getOne(id);
        List<PedidoSaidaParcela> parcela = parcelaRepo.findByIdPedidoSaida(id).orElse(null);
        List<PedidoSaidaProdutoGetDto> produtos = produtoService.getAllIdPedido(0, 200, id);
        DadosEmpresaGetDto empresa = dadosEmpresaService.get();
        PdfPedidoDados pdf = new PdfPedidoDados();

        pdf.codigo = pedido.id.toString();
        pdf.nomeEmpresa = empresa.getNome();
        try {
            MaskFormatter mask = new MaskFormatter("###.###.###/####-##");
            mask.setValueContainsLiteralCharacters(false);
            String cnpjFor = mask.valueToString(empresa.getCnpj());
            pdf.cnpjEmpresa = String.format("%s", cnpjFor);
        } catch (Exception e) {
            pdf.cnpjEmpresa = String.format("%s", empresa.getCnpj());
        }

        pdf.emailEmpresa = empresa.getEmail();

        try {
            MaskFormatter mask = new MaskFormatter("(##) # ####-####");
            mask.setValueContainsLiteralCharacters(false);
            String format = mask.valueToString(empresa.getTelefone());
            pdf.telefoneEmpresa = String.format("%s", format);
        } catch (Exception e) {
            pdf.telefoneEmpresa = String.format("%s", empresa.getTelefone());
        }

        pdf.nomeClienteEmpresa = String.format("%s", pedido.cliente.nomeEmpresa());

        if(pedido.cliente.tipo() == TipoCliente.pessoaFisica){
            try {
                MaskFormatter mask = new MaskFormatter("###.###.###-##");
                mask.setValueContainsLiteralCharacters(false);
                String cpfFor = mask.valueToString(pedido.cliente.cpfCnpj());
                pdf.cnpjCliente = String.format("%s", cpfFor);
            } catch (Exception e) {
                pdf.cnpjCliente = String.format("%s", pedido.cliente.cpfCnpj());
            }
        } else {
            try {
                MaskFormatter mask = new MaskFormatter("###.###.###/####-##");
                mask.setValueContainsLiteralCharacters(false);
                String cnpjFor = mask.valueToString(pedido.cliente.cpfCnpj());
                pdf.cnpjCliente = String.format("%s", cnpjFor);
            } catch (Exception e) {
                pdf.cnpjCliente = String.format("%s", pedido.cliente.cpfCnpj());
            }
        }

        pdf.enderecoCliente = String.format("%s %s, %s, %s %s-%s",
                pedido.cliente.cep(),
                pedido.cliente.rua(),
                pedido.cliente.numero(),
                pedido.cliente.bairro(),
                pedido.cliente.cidade(),
                pedido.cliente.estado().name()
        );

        pdf.emailCliente = String.format("%s", pedido.cliente.email());

        try {
            MaskFormatter mask = new MaskFormatter("(##) # ####-####");
            mask.setValueContainsLiteralCharacters(false);
            String format = mask.valueToString(pedido.cliente.telefone());
            pdf.telefoneCliente = String.format("%s", format);
        } catch (Exception e) {
            pdf.telefoneCliente = String.format("%s", pedido.cliente.telefone());
        }


        pdf.dataPedido = new SimpleDateFormat("dd/MM/yyyy").format(pedido.dataVigencia);
        pdf.nomeVendedor = String.format("%s", pedido.vendedor.nome());
        pdf.nomeCliente = String.format("%s", pedido.cliente.nome());
        pdf.condicaoPagamento = "Condição de Pagamento: Carteira 01X";

        pdf.itens = new ArrayList<PdfPedidoItens>();

        for(PedidoSaidaProdutoGetDto prod : produtos){
            PdfPedidoItens pdfItem = new PdfPedidoItens();
            pdfItem.codigo = prod.getProduto().id().toString();
            pdfItem.descricao = prod.getProduto().nome();
            pdfItem.quantidade = prod.getQtde().toString();
            pdfItem.valorUnitario = String.format("%.2f", prod.getValorUnitario());
            pdfItem.valorTotal = String.format("%.2f", prod.getValorTotal());
            pdf.itens.add(pdfItem);
        }

        pdf.valorTotal = String.format("Total: R$ %.2f\n\n", pedido.valorTotal);
        return pdf;
    }

    public static void documento1(Document document, PdfPedidoDados dados){
        try {
            Font fontTitulo = new Font(Font.HELVETICA, 14, Font.BOLD);
            Font fontGrandeB = new Font(Font.HELVETICA, 12, Font.BOLD);
            Font fontGrande = new Font(Font.HELVETICA, 12, Font.NORMAL);
            Font fontMediaB = new Font(Font.HELVETICA, 10, Font.BOLD);
            Font fontMedia = new Font(Font.HELVETICA, 10, Font.NORMAL);
            Font fontPequena = new Font(Font.HELVETICA, 8, Font.NORMAL);

            // Tabela dados Empresa
            {
                PdfPCell cellEmpresa = new PdfPCell();
                cellEmpresa.setBorderWidth(0f);
                cellEmpresa.setPadding(10f);
                cellEmpresa.setPaddingTop(0f);
                //cellEmpresa.setBorderColor(new Color(0, 0, 0));

                Image img = Image.getInstance("./logo.jpg");
                img.setAlignment(Image.ALIGN_CENTER);

                Paragraph p1 = new Paragraph(dados.nomeEmpresa, fontTitulo);
                p1.setAlignment(Element.ALIGN_CENTER);

                Paragraph p2 = new Paragraph(dados.cnpjEmpresa, fontGrande);
                p2.setAlignment(Element.ALIGN_CENTER);

                Paragraph p3 = new Paragraph(dados.telefoneEmpresa, fontGrande);
                p3.setAlignment(Element.ALIGN_CENTER);

                Paragraph p4 = new Paragraph(dados.emailEmpresa, fontGrande);
                p4.setAlignment(Element.ALIGN_CENTER);

                cellEmpresa.addElement(p1);
                cellEmpresa.addElement(p2);
                cellEmpresa.addElement(p3);
                cellEmpresa.addElement(p4);

                PdfPTable table = new PdfPTable(2);
                table.setWidthPercentage(100);
                table.setWidths(new float[]{2, 12});
                table.addCell(img);
                table.addCell(cellEmpresa);
                document.add(table);
            }

            //Titulo dados Cliente
            {
                Paragraph p1 = new Paragraph("Cliente", fontGrandeB);
                p1.setSpacingAfter(5);
                document.add(p1);
            }

            //Tabela Dados Cliente
            {
                PdfPTable table = new PdfPTable(4);
                table.setWidthPercentage(100);

                var p1 = new PdfPCell();
                p1.addElement(new Phrase("Nome:", fontPequena));
                p1.addElement(new Phrase(dados.nomeCliente, fontGrandeB));
                p1.setColspan(3);
                p1.setUseAscender(true);
                p1.setUseDescender(true);
                p1.setPadding(2);

                var p2 = new PdfPCell();
                p2.addElement(new Phrase("Código:", fontMedia));
                p2.addElement(new Phrase(dados.codigo, fontGrandeB));
                p2.setUseAscender(true);
                p2.setUseDescender(true);
                p2.setPadding(2);

                var p3 = new PdfPCell();
                p3.addElement(new Phrase("CNPJ/CPF:", fontPequena));
                p3.addElement(new Phrase(dados.cnpjCliente, fontMedia));
                p3.setUseAscender(true);
                p3.setUseDescender(true);
                p3.setPadding(2);

                var p4 = new PdfPCell();
                p4.addElement(new Phrase("E-Mail:", fontPequena));
                p4.addElement(new Phrase(dados.emailCliente, fontMedia));
                p4.setUseAscender(true);
                p4.setUseDescender(true);
                p4.setPadding(2);

                var p5 = new PdfPCell();
                p5.addElement(new Phrase("Telefone:", fontPequena));
                p5.addElement(new Phrase(dados.telefoneCliente, fontMedia));
                p5.setUseAscender(true);
                p5.setUseDescender(true);
                p5.setPadding(2);

                var p6 = new PdfPCell();
                p6.addElement(new Phrase("Data:", fontPequena));
                p6.addElement(new Phrase(dados.dataPedido, fontMedia));
                p6.setUseAscender(true);
                p6.setUseDescender(true);
                p6.setPadding(2);

                var p7 = new PdfPCell();
                p7.addElement(new Phrase("Endereço:", fontPequena));
                p7.addElement(new Phrase(dados.enderecoCliente, fontMedia));
                p7.setUseAscender(true);
                p7.setUseDescender(true);
                p7.setPadding(2);
                p7.setColspan(3);

                var p8 = new PdfPCell();
                p8.addElement(new Phrase("Vendedor:", fontPequena));
                p8.addElement(new Phrase(dados.nomeVendedor, fontMedia));
                p8.setUseAscender(true);
                p8.setUseDescender(true);
                p8.setPadding(2);

                table.addCell(p1);
                table.addCell(p2);
                table.addCell(p3);
                table.addCell(p4);
                table.addCell(p5);
                table.addCell(p6);
                table.addCell(p7);
                table.addCell(p8);
                document.add(table);
            }
            {
                Paragraph p1 = new Paragraph("Itens:", fontGrandeB);
                p1.setSpacingAfter(5);
                document.add(p1);
            }
            {
                PdfPTable table = new PdfPTable(6);
                table.setWidths(new float[]{1, 2, 10, 2, 2, 2});
                table.setWidthPercentage(100);

                Paragraph p1 = new Paragraph("#", fontMedia);
                p1.setAlignment(Element.ALIGN_RIGHT);
                table.addCell(p1);

                Paragraph p2 = new Paragraph("Código", fontMedia);
                p2.setAlignment(Element.ALIGN_RIGHT);
                table.addCell(p2);

                Paragraph p3 = new Paragraph("Descrição", fontMedia);
                table.addCell(p3);

                Paragraph p4 = new Paragraph("Qtde.", fontMedia);
                p4.setAlignment(Element.ALIGN_RIGHT);
                table.addCell(p4);

                Paragraph p6 = new Paragraph("Valor Unid", fontMedia);
                p6.setAlignment(Element.ALIGN_RIGHT);
                table.addCell(p6);//.setHorizontalAlignment(Element.ALIGN_RIGHT);

                Paragraph p7 = new Paragraph("Total", fontMedia);
                p7.setAlignment(Element.ALIGN_RIGHT);
                table.addCell(p7);

                Integer i = 1;
                for (PdfPedidoItens item : dados.itens) {
                    Paragraph pi1 = new Paragraph(i.toString(), fontMedia);
                    pi1.setAlignment(Element.ALIGN_RIGHT);
                    table.addCell(pi1);

                    Paragraph pi2 = new Paragraph(item.codigo, fontMedia);
                    pi2.setAlignment(Element.ALIGN_RIGHT);
                    table.addCell(pi2);

                    Paragraph pi3 = new Paragraph(item.descricao, fontMedia);
                    table.addCell(pi3);

                    Paragraph pi4 = new Paragraph(item.quantidade, fontMedia);
                    pi4.setAlignment(Element.ALIGN_RIGHT);
                    table.addCell(pi4);

                    Paragraph pi5 = new Paragraph(item.valorUnitario, fontMedia);
                    pi5.setAlignment(Element.ALIGN_RIGHT);
                    table.addCell(pi5);

                    Paragraph pi6 = new Paragraph(item.valorTotal, fontMedia);
                    pi6.setAlignment(Element.ALIGN_RIGHT);
                    table.addCell(pi6);

                    i++;
                }
                document.add(table);
                Paragraph p = new Paragraph(dados.valorTotal, fontGrandeB);
                p.setAlignment(Element.ALIGN_RIGHT);
                document.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}