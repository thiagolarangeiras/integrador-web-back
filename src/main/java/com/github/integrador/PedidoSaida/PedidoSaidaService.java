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
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.swing.text.MaskFormatter;
import java.awt.*;
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
        pdf.nomeEmpresa = empresa.getNome();

        try {
            MaskFormatter mask = new MaskFormatter("###.###.###/####-##");
            mask.setValueContainsLiteralCharacters(false);
            String cnpjFor = mask.valueToString(empresa.getCnpj());
            pdf.cnpjEmpresa = String.format("CNPJ: %s IE: %s", cnpjFor, empresa.getId());
        } catch (Exception e) {
            pdf.cnpjEmpresa = String.format("CNPJ: %s IE: %s", empresa.getCnpj(), empresa.getId());
        }

        pdf.emailEmpresa = empresa.getEmail();

        try {
            MaskFormatter mask = new MaskFormatter("(##) # ####-####");
            mask.setValueContainsLiteralCharacters(false);
            String format = mask.valueToString(empresa.getTelefone());
            pdf.telefoneEmpresa = String.format("Telefone: %s", format);
        } catch (Exception e) {
            pdf.telefoneEmpresa = String.format("Telefone: %s", empresa.getTelefone());
        }

        pdf.nomeClienteEmpresa = String.format("Cliente: %s", pedido.cliente.nomeEmpresa());

        if(pedido.cliente.tipo() == TipoCliente.pessoaFisica){
            try {
                MaskFormatter mask = new MaskFormatter("###.###.###-##");
                mask.setValueContainsLiteralCharacters(false);
                String cpfFor = mask.valueToString(pedido.cliente.cpfCnpj());
                pdf.cnpjCliente = String.format("CPF: %s", cpfFor);
            } catch (Exception e) {
                pdf.cnpjEmpresa = String.format("CPF: %s", pedido.cliente.cpfCnpj());
            }
        } else {
            try {
                MaskFormatter mask = new MaskFormatter("###.###.###/####-##");
                mask.setValueContainsLiteralCharacters(false);
                String cnpjFor = mask.valueToString(pedido.cliente.cpfCnpj());
                pdf.cnpjCliente = String.format("CNPJ: %s", cnpjFor);
            } catch (Exception e) {
                pdf.cnpjEmpresa = String.format("CNPJ: %s", pedido.cliente.cpfCnpj());
            }
        }

        pdf.enderecoCliente = String.format("Endereço: %s, %s, %s - %s/%s - ",
                pedido.cliente.rua(),
                pedido.cliente.numero(),
                pedido.cliente.bairro(),
                pedido.cliente.cidade(),
                pedido.cliente.estado().name(),
                pedido.cliente.cep()
        );

        pdf.emailCliente = String.format("E-mail: %s", pedido.cliente.email());

        try {
            MaskFormatter mask = new MaskFormatter("(##) # ####-####");
            mask.setValueContainsLiteralCharacters(false);
            String format = mask.valueToString(pedido.cliente.telefone());
            pdf.telefoneCliente = String.format("Telefone: %s", format);
        } catch (Exception e) {
            pdf.telefoneCliente = String.format("Telefone: %s", pedido.cliente.telefone());
        }

        pdf.dataPedido = "Data: 05/02/2025 - 13:00";
        pdf.nomeVendedor = String.format("Vendador: %s", pedido.vendedor.nome());
        pdf.nomeCliente = String.format("Cliente: %s", pedido.cliente.nome());
        pdf.condicaoPagamento = "Condição de Pagamento: Carteira 01X";

        pdf.itens = new ArrayList<PdfPedidoItens>();

        for(PedidoSaidaProdutoGetDto prod : produtos){
            PdfPedidoItens pdfItem = new PdfPedidoItens();
            pdfItem.codigo = prod.getProduto().id().toString();
            pdfItem.descricao = prod.getProduto().nome();
            pdfItem.situacaoTributaria = "";
            pdfItem.quantidade = prod.getQtde().toString();
            pdfItem.valorUnitario = prod.getValorUnitario().toString();
            pdfItem.valorTotal = prod.getValorTotal().toString();
            pdf.itens.add(pdfItem);
        }

        pdf.valorTotal = String.format("Total: R$ %.2f\n\n", pedido.valorTotal);
        return pdf;
    }

    public static void documento1(Document document, PdfPedidoDados dados){
        try {
            Font fontTitulo = new Font(Font.HELVETICA, 16, Font.BOLD);
            Font fontNormal = new Font(Font.HELVETICA, 12, Font.NORMAL);
            Font fontItem = new Font(Font.HELVETICA, 10, Font.NORMAL);
            {
                PdfPCell cellEmpresa = new PdfPCell();
                cellEmpresa.setBorderWidth(1f);
                cellEmpresa.setPadding(10f);
                cellEmpresa.setBorderColor(new Color(0, 0, 0));

                Paragraph p1 = new Paragraph(dados.nomeEmpresa, fontTitulo);
                p1.setAlignment(Element.ALIGN_CENTER);

                Paragraph p2 = new Paragraph(dados.cnpjEmpresa, fontNormal);
                p2.setAlignment(Element.ALIGN_CENTER);

                Paragraph p3 = new Paragraph(dados.telefoneEmpresa, fontNormal);
                p3.setAlignment(Element.ALIGN_CENTER);

                Paragraph p4 = new Paragraph(dados.emailEmpresa, fontNormal);
                p4.setAlignment(Element.ALIGN_CENTER);

                cellEmpresa.addElement(p1);
                cellEmpresa.addElement(p2);
                cellEmpresa.addElement(p3);
                cellEmpresa.addElement(p4);

                PdfPTable table = new PdfPTable(1);
                table.setWidthPercentage(100);
                table.addCell(cellEmpresa);
                document.add(table);
            }
            {
                Paragraph p1 = new Paragraph("Cliente", fontTitulo);
                p1.setSpacingAfter(5);
                document.add(p1);
            }
            {
                PdfPCell cellCliente = new PdfPCell();
                cellCliente.setBorderWidth(1f);
                cellCliente.setPadding(10f);
                cellCliente.setPaddingTop(0f);
                cellCliente.setBorderColor(new Color(0, 0, 0));

                Paragraph p1 = new Paragraph(dados.nomeCliente, fontTitulo);
                //p1.setAlignment(Element.ALIGN_CENTER);

                Paragraph p2 = new Paragraph(dados.cnpjCliente, fontNormal);
                //p2.setAlignment(Element.ALIGN_CENTER);

                Paragraph p3 = new Paragraph(dados.enderecoCliente, fontNormal);
                //p3.setAlignment(Element.ALIGN_CENTER);

                Paragraph p4 = new Paragraph(dados.emailCliente, fontNormal);
                //p4.setAlignment(Element.ALIGN_CENTER);

                Paragraph p5 = new Paragraph(dados.telefoneCliente, fontNormal);
                //p5.setAlignment(Element.ALIGN_CENTER);

                Paragraph p6 = new Paragraph(dados.dataPedido, fontNormal);
                //p6.setAlignment(Element.ALIGN_CENTER);

                Paragraph p7 = new Paragraph(dados.nomeVendedor, fontNormal);
                //p7.setAlignment(Element.ALIGN_CENTER);

                cellCliente.addElement(p1);
                cellCliente.addElement(p2);
                cellCliente.addElement(p3);
                cellCliente.addElement(p4);
                cellCliente.addElement(p5);
                cellCliente.addElement(p6);
                cellCliente.addElement(p7);

                PdfPTable table = new PdfPTable(1);
                table.setWidthPercentage(100);
                table.addCell(cellCliente);
                document.add(table);
            }
            {
                Paragraph p1 = new Paragraph("Itens:", fontTitulo);
                p1.setSpacingAfter(5);
                document.add(p1);
            }
            {
                PdfPTable table = new PdfPTable(6);
                table.setWidths(new float[]{1, 2, 10, 2, 2, 2});
                table.setWidthPercentage(100);

                Paragraph p1 = new Paragraph("#", fontItem);
                p1.setAlignment(Element.ALIGN_RIGHT);
                table.addCell(p1);

                Paragraph p2 = new Paragraph("Código", fontItem);
                p2.setAlignment(Element.ALIGN_RIGHT);
                table.addCell(p2);

                Paragraph p3 = new Paragraph("Descrição", fontItem);
                table.addCell(p3);

                Paragraph p4 = new Paragraph("Qtde.", fontItem);
                p4.setAlignment(Element.ALIGN_RIGHT);
                table.addCell(p4);

                Paragraph p6 = new Paragraph("Valor Unid", fontItem);
                p6.setAlignment(Element.ALIGN_RIGHT);
                table.addCell(p6);//.setHorizontalAlignment(Element.ALIGN_RIGHT);

                Paragraph p7 = new Paragraph("Total", fontItem);
                p7.setAlignment(Element.ALIGN_RIGHT);
                table.addCell(p7);

                Integer i = 1;
                for (PdfPedidoItens item : dados.itens) {
                    Paragraph pi1 = new Paragraph(i.toString(), fontItem);
                    pi1.setAlignment(Element.ALIGN_RIGHT);
                    table.addCell(pi1);

                    Paragraph pi2 = new Paragraph(item.codigo, fontItem);
                    pi2.setAlignment(Element.ALIGN_RIGHT);
                    table.addCell(pi2);

                    Paragraph pi3 = new Paragraph(item.descricao, fontItem);
                    table.addCell(pi3);

                    Paragraph pi4 = new Paragraph(item.quantidade, fontItem);
                    pi4.setAlignment(Element.ALIGN_RIGHT);
                    table.addCell(pi4);

                    Paragraph pi5 = new Paragraph(item.valorUnitario, fontItem);
                    pi5.setAlignment(Element.ALIGN_RIGHT);
                    table.addCell(pi5);

                    Paragraph pi6 = new Paragraph(item.valorTotal, fontItem);
                    pi6.setAlignment(Element.ALIGN_RIGHT);
                    table.addCell(pi6);

                    i++;
                }
                document.add(table);
                Paragraph p = new Paragraph(dados.valorTotal, fontTitulo);
                p.setAlignment(Element.ALIGN_RIGHT);
                document.add(p);
            }

            //document.add(new Paragraph(dados.condicaoPagamento, fontNormal));

            PdfPTable tabelaAssinatura = new PdfPTable(2);
            tabelaAssinatura.setWidthPercentage(100);
            tabelaAssinatura.addCell(dados.nomeVendedor);
            tabelaAssinatura.addCell(dados.nomeCliente);

            document.add(tabelaAssinatura);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}