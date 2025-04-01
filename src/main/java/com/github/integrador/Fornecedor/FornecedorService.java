package com.github.integrador.Fornecedor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FornecedorService {
    @Autowired
    private FornecedorRepo fornecedorRepo;

    public List<FornecedorGetDto> getAll (int page, int count) {
        Pageable pageable = PageRequest.of(page, count);
        return fornecedorRepo.findAll(pageable)
                .stream()
                .map(Fornecedor::mapToDto)
                .collect(Collectors.toList());
    }

    public FornecedorGetDto getOne (Integer id) {
        Optional<Fornecedor> fornecedorOptional = fornecedorRepo.findById(id);
        Fornecedor fornecedor = fornecedorOptional.orElseThrow();;
        return Fornecedor.mapToDto(fornecedor);
    }

    public FornecedorGetDto post(FornecedorPostDto dto) {
        Fornecedor fornecedor = Fornecedor.mapToObj(dto);
        fornecedor = fornecedorRepo.save(fornecedor);
        return Fornecedor.mapToDto(fornecedor);
    }

    public FornecedorGetDto patch(Integer id, FornecedorPostDto dto) {
        Fornecedor fornecedor = fornecedorRepo.findById(id).orElseThrow();
        fornecedor = Fornecedor.mapToObj(dto);
        fornecedor.setId(id);
        fornecedor = fornecedorRepo.save(fornecedor);
        return Fornecedor.mapToDto(fornecedor);
    }

    public void delete(Integer id) {
        fornecedorRepo.deleteById(id);
    }
}
