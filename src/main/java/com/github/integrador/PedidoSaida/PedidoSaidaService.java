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
import com.github.integrador.PedidoSaidaProduto.PedidoSaidaProduto;
import com.github.integrador.PedidoSaidaProduto.PedidoSaidaProdutoRepo;
import com.github.integrador.enums.TipoCliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.swing.text.MaskFormatter;
import java.text.ParseException;
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
            pdf.telefoneEmpresa = String.format("Telefone: %s\n\n", format);
        } catch (Exception e) {
            pdf.telefoneEmpresa = String.format("Telefone: %s\n\n", empresa.getTelefone());
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
            pdf.telefoneCliente = String.format("Telefone: %s\n\n", format);
        } catch (Exception e) {
            pdf.telefoneCliente = String.format("Telefone: %s\n\n", pedido.cliente.telefone());
        }

        pdf.dataPedido = "Data: 05/02/2025 - 13:00";
        pdf.nomeVendedor = String.format("Vendador: %s", pedido.vendedor.nome());
        pdf.nomeCliente = String.format("Cliente: %s", pedido.cliente.nome());
        pdf.condicaoPagamento = "\nCondição de Pagamento: Carteira 01X";

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
}