package com.github.integrador.PedidoEntrada;


import com.github.integrador.Cliente.Cliente;
import com.github.integrador.Fornecedor.FornecedorGetDto;
import com.github.integrador.Fornecedor.FornecedorRepo;
import com.github.integrador.Fornecedor.FornecedorService;
import com.github.integrador.PedidoEntradaParcela.PedidoEntradaParcela;
import com.github.integrador.PedidoEntradaParcela.PedidoEntradaParcelaService;
import com.github.integrador.PedidoEntradaProduto.PedidoEntradaProduto;
import com.github.integrador.PedidoEntradaProduto.PedidoEntradaProdutoService;
import com.github.integrador.Vendedor.VendedorGetDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PedidoEntradaService {
    @Autowired private PedidoEntradaRepo repo;
    @Autowired private FornecedorRepo fornecedorRepo;
    @Autowired private FornecedorService fornecedorService;
    @Autowired private PedidoEntradaProdutoService pedidoEntradaProdutoService;
    @Autowired private PedidoEntradaParcelaService pedidoEntradaParcelaService;

    public List<PedidoEntradaGetDto> getAll(
            int page,
            int count,
            String nomeFornecedor,
            Date dataInicio,
            Date dataFim,
            Boolean dataOrdemAsc,
            Boolean dataOrdemDec,
            Integer id
    ) {
        Pageable pageable = PageRequest.of(page, count);
        if (nomeFornecedor.isBlank()){
            fornecedorRepo.findByNomeContainingIgnoreCase(nomeFornecedor, pageable);
        }

        if (dataOrdemAsc){
            repo.findAllByOrderByDataVigenciaAsc(pageable);
        }

        return repo.findAll(pageable)
                .stream()
                .map(PedidoEntrada::mapToDto)
                .toList();
    }

    public PedidoEntradaGetDto getOne (Integer id) {
        Optional<PedidoEntrada> pedidoentradaOptional = repo.findById(id);
        PedidoEntrada pedidoentrada = pedidoentradaOptional.orElseThrow();
        PedidoEntradaGetDto dto = PedidoEntrada.mapToDto(pedidoentrada);

        dto.fornecedor = fornecedorService.getOne(dto.getIdFornecedor());
        dto.pedidoProdutos = pedidoEntradaProdutoService.getByIdPedido(dto.getId());
        dto.pedidoParcelas = pedidoEntradaParcelaService.getByIdPedido(dto.getId());
        return dto;
    }

    public PedidoEntradaGetDto post(PedidoEntradaPostDto dto) {
        PedidoEntrada pedidoentrada = PedidoEntrada.mapToObj(dto);
        pedidoentrada = repo.save(pedidoentrada);
        return PedidoEntrada.mapToDto(pedidoentrada);
    }

    public PedidoEntradaGetDto patch(Integer id, PedidoEntradaPostDto dto) {
        PedidoEntrada pedidoentrada  = repo.findById(id).orElseThrow();
        pedidoentrada = PedidoEntrada.mapToObj(dto);
        pedidoentrada.setId(id);
        pedidoentrada = repo.save(pedidoentrada);
        return PedidoEntrada.mapToDto(pedidoentrada);
    }

    public void delete(Integer id) {
        repo.deleteById(id);
    }
}
