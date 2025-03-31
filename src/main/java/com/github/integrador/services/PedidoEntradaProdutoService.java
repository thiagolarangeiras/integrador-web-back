package com.github.integrador.services;

import com.github.integrador.dtos.PedidoEntradaProdutoGetDto;
import com.github.integrador.dtos.PedidoEntradaProdutoPostDto;
import com.github.integrador.models.PedidoEntradaProduto;
import com.github.integrador.repositories.PedidoEntradaProdutoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PedidoEntradaProdutoService {
    @Autowired
    private PedidoEntradaProdutoRepo pedidoentradaprodutoRepo;

    public List<PedidoEntradaProdutoGetDto> getAll (int page, int count) {
        Pageable pageable = PageRequest.of(page, count);
        return pedidoentradaprodutoRepo.findAll(pageable)
                .stream()
                .map(PedidoEntradaProduto::mapToDto)
                .collect(Collectors.toList());
    }

    public PedidoEntradaProdutoGetDto getOne (Integer id) {
        Optional<PedidoEntradaProduto> pedidoentradaprodutoOptional = pedidoentradaprodutoRepo.findById(id);
        PedidoEntradaProduto pedidoentradaproduto = pedidoentradaprodutoOptional.orElseThrow();;
        return PedidoEntradaProduto.mapToDto(pedidoentradaproduto);
    }

    public PedidoEntradaProdutoGetDto post(PedidoEntradaProdutoPostDto dto) {
        PedidoEntradaProduto pedidoentradaproduto = PedidoEntradaProduto.mapToObj(dto);
        pedidoentradaproduto = pedidoentradaprodutoRepo.save(pedidoentradaproduto);
        return PedidoEntradaProduto.mapToDto(pedidoentradaproduto);
    }

    public PedidoEntradaProdutoGetDto patch(Integer id, PedidoEntradaProdutoPostDto dto) {
        PedidoEntradaProduto pedidoentradaproduto = pedidoentradaprodutoRepo.findById(id).orElseThrow();
        pedidoentradaproduto = PedidoEntradaProduto.mapToObj(dto);
        pedidoentradaproduto.setId(id);
        pedidoentradaproduto = pedidoentradaprodutoRepo.save(pedidoentradaproduto);
        return PedidoEntradaProduto.mapToDto(pedidoentradaproduto);
    }

    public void delete(Integer id) {
        pedidoentradaprodutoRepo.deleteById(id);
    }
}

