package com.github.integrador.PedidoEntrada;

import com.github.integrador.Fornecedor.FornecedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoEntradaService {
    @Autowired private PedidoEntradaRepo repo;
    @Autowired private FornecedorService fornecedorService;

    public List<PedidoEntradaGetDto> getAll(Integer page, Integer count) {
        Pageable pageable = PageRequest.of(page, count);
        return repo.findAll(pageable)
                .stream()
                .map(PedidoEntrada::mapToDto)
                .toList();
    }

    public PedidoEntradaGetDto getOne (Integer id) {
        if(id == null) return null;
        PedidoEntrada pedidoentrada = repo.findById(id).get();
        PedidoEntradaGetDto dto = PedidoEntrada.mapToDto(pedidoentrada);
        dto.fornecedor = fornecedorService.getOne(dto.getIdFornecedor());
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
