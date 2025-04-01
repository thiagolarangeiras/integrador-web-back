package com.github.integrador.PedidoSaidaProduto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PedidoSaidaProdutoService {
    @Autowired
    private PedidoSaidaProdutoRepo repo;

    public List<PedidoSaidaProdutoGetDto> getAll (int page, int count) {
        Pageable pageable = PageRequest.of(page, count);
        return repo.findAll(pageable)
                .stream()
                .map(PedidoSaidaProduto::mapToDto)
                .collect(Collectors.toList());
    }

    public PedidoSaidaProdutoGetDto getOne (Integer id) {
        Optional<PedidoSaidaProduto> optional = repo.findById(id);
        PedidoSaidaProduto obj = optional.orElseThrow();;
        return PedidoSaidaProduto.mapToDto(obj);
    }

    public PedidoSaidaProdutoGetDto post(PedidoSaidaProdutoPostDto dto) {
        PedidoSaidaProduto obj = PedidoSaidaProduto.mapToObj(dto);
        obj = repo.save(obj);
        return PedidoSaidaProduto.mapToDto(obj);
    }

    public PedidoSaidaProdutoGetDto patch(Integer id, PedidoSaidaProdutoPostDto dto) {
        PedidoSaidaProduto obj = repo.findById(id).orElseThrow();
        obj = PedidoSaidaProduto.mapToObj(dto);
        obj.setId(id);
        obj = repo.save(obj);
        return PedidoSaidaProduto.mapToDto(obj);
    }

    public void delete(Integer id) {
        repo.deleteById(id);
    }
}