package com.github.integrador.Produto;

import com.github.integrador.Fornecedor.FornecedorGetDto;
import com.github.integrador.Fornecedor.FornecedorService;
import com.github.integrador.Marca.Marca;
import com.github.integrador.Marca.MarcaGetDto;
import com.github.integrador.Marca.MarcaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepo produtoRepo;

    @Autowired
    private MarcaService marcaService;

    @Autowired
    private FornecedorService fornecedorService;

    public List<ProdutoGetDto> getAll (int page, int count) {
        Pageable pageable = PageRequest.of(page, count);
        List<ProdutoGetDto> dtos = new ArrayList<>();
        for (Produto produto : produtoRepo.findAll(pageable)){
            MarcaGetDto dtoMarca = marcaService.getOne(produto.getIdMarca());
            FornecedorGetDto dtoFornecedor = fornecedorService.getOne(produto.getIdFornecedor());
            dtos.add(Produto.mapToDto(produto, dtoMarca, dtoFornecedor));
        }
        return dtos;
    }

    public List<ProdutoGetDto> getAllFilter (Integer page, Integer count, String nome) {
        Pageable pageable = PageRequest.of(page, count);
        List<ProdutoGetDto> dtos = new ArrayList<>();
        for (Produto produto : produtoRepo.findByNomeContaining(nome, pageable).orElseThrow() ){
            MarcaGetDto dtoMarca = marcaService.getOne(produto.getIdMarca());
            FornecedorGetDto dtoFornecedor = fornecedorService.getOne(produto.getIdFornecedor());
            dtos.add(Produto.mapToDto(produto, dtoMarca, dtoFornecedor));
        }
        return dtos;
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




