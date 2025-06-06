package com.github.integrador.PedidoEntradaProduto;

import com.github.integrador.Fornecedor.Fornecedor;
import com.github.integrador.PedidoSaidaProduto.PedidoSaidaProduto;
import com.github.integrador.PedidoSaidaProduto.PedidoSaidaProdutoGetDto;
import com.github.integrador.Produto.Produto;
import com.github.integrador.Produto.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PedidoEntradaProdutoService {
    @Autowired private PedidoEntradaProdutoRepo repo;
    @Autowired private ProdutoService produtoService;

    public List<PedidoEntradaProdutoGetDto> getAll (int page, int count) {
        Pageable pageable = PageRequest.of(page, count);
        return repo.findAll(pageable)
                .stream()
                .map(PedidoEntradaProduto::mapToDto)
                .collect(Collectors.toList());
    }

    public List<PedidoEntradaProdutoGetDto> getAllIdPedido (Integer page, Integer count, Integer idPedido) {
        Pageable pageable = PageRequest.of(page, count);
        List<PedidoEntradaProdutoGetDto> dtos = repo.findByPedido(idPedido, pageable)
                .stream()
                .map(PedidoEntradaProduto::mapToDto)
                .toList();
        for(PedidoEntradaProdutoGetDto dto : dtos){
            dto.produto = produtoService.getOne(dto.idProduto);
        }
        return dtos;
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

    public List<PedidoEntradaProdutoGetDto> getByIdPedido(Integer id){
        Optional<List<PedidoEntradaProduto>> produtoOptional = repo.findByIdPedidoEntrada(id);
        if(produtoOptional.isPresent()){
            return produtoOptional.get()
                    .stream()
                    .map(PedidoEntradaProduto::mapToDto)
                    .toList();
        }
        return null;
    }
}

