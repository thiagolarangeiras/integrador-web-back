package com.github.integrador.PedidoEntradaProduto;

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
    private PedidoEntradaProdutoRepo repo;

    public List<PedidoEntradaProdutoGetDto> getAll (int page, int count) {
        Pageable pageable = PageRequest.of(page, count);
        return repo.findAll(pageable)
                .stream()
                .map(PedidoEntradaProduto::mapToDto)
                .collect(Collectors.toList());
    }

    public PedidoEntradaProdutoGetDto getOne (Integer id) {
        Optional<PedidoEntradaProduto> pedidoentradaprodutoOptional = repo.findById(id);
        PedidoEntradaProduto pedidoentradaproduto = pedidoentradaprodutoOptional.orElseThrow();;
        return PedidoEntradaProduto.mapToDto(pedidoentradaproduto);
    }

    public PedidoEntradaProdutoGetDto post(PedidoEntradaProdutoPostDto dto) {
        PedidoEntradaProduto pedidoentradaproduto = PedidoEntradaProduto.mapToObj(dto);
        pedidoentradaproduto = repo.save(pedidoentradaproduto);
        return PedidoEntradaProduto.mapToDto(pedidoentradaproduto);
    }

    public PedidoEntradaProdutoGetDto patch(Integer id, PedidoEntradaProdutoPostDto dto) {
        PedidoEntradaProduto pedidoentradaproduto = repo.findById(id).orElseThrow();
        pedidoentradaproduto = PedidoEntradaProduto.mapToObj(dto);
        pedidoentradaproduto.setId(id);
        pedidoentradaproduto = repo.save(pedidoentradaproduto);
        return PedidoEntradaProduto.mapToDto(pedidoentradaproduto);
    }

    public void delete(Integer id) {
        repo.deleteById(id);
    }
}

