package com.github.integrador.Produto;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepo produtoRepo;

    public List<ProdutoGetDto> getAll (int page, int count) {
        Pageable pageable = PageRequest.of(page, count);
        return produtoRepo.findAll(pageable)
                .stream()
                .map(Produto::mapToDto)
                .collect(Collectors.toList());
    }

    public ProdutoGetDto getOne (Integer id) {
        Optional<Produto> produtoOptional = produtoRepo.findById(id);
        Produto produto = produtoOptional.orElseThrow();;
        return Produto.mapToDto(produto);
    }

    public ProdutoGetDto post(ProdutoPostDto dto) {
        Produto produto = Produto.mapToObj(dto);
        produto = produtoRepo.save(produto);
        return Produto.mapToDto(produto);
    }

    public ProdutoGetDto patch(Integer id, ProdutoPostDto dto) {
        Produto produto  = produtoRepo.findById(id).orElseThrow();
        produto = Produto.mapToObj(dto);
        produto.setId(id);
        produto = produtoRepo.save(produto);
        return Produto.mapToDto(produto);
    }

    public void delete(Integer id) {
        produtoRepo.deleteById(id);
    }
}




