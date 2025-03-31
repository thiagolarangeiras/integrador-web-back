package com.github.integrador.services;

import com.github.integrador.dtos.FornecedorGetDto;
import com.github.integrador.dtos.FornecedorPostDto;
import com.github.integrador.models.Fornecedor;
import com.github.integrador.repositories.FornecedorRepository;
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
    private FornecedorRepository fornecedorRepository;

    public List<FornecedorGetDto> getAll (int page, int count) {
        Pageable pageable = PageRequest.of(page, count);
        return fornecedorRepository.findAll(pageable)
                .stream()
                .map(Fornecedor::mapToDto)
                .collect(Collectors.toList());
    }

    /*Pageable pageable = PageRequest.of(page, count);
        return fornecedorRepository.findById(id, pageable)
            .orElseThrow()
            .stream()
            .map(Fornecedor::mapToDto)
            .collect(Collectors.toList());
     */

    public FornecedorGetDto getOne (Integer id) {
        Optional<Fornecedor> fornecedorOptional = fornecedorRepository.findById(id);
        if (!fornecedorOptional.isPresent()){
            //
        }

        Fornecedor fornecedor = fornecedorOptional.orElseThrow();;
        return Fornecedor.mapToDto(fornecedor);
    }

    public FornecedorGetDto post(FornecedorPostDto dto) {
        Fornecedor fornecedor = Fornecedor.mapToObj(dto);
        fornecedor = fornecedorRepository.save(fornecedor);
        return Fornecedor.mapToDto(fornecedor);
    }

    public FornecedorGetDto patch(Integer id, FornecedorPostDto dto) {
        Fornecedor fornecedor = fornecedorRepository.findById(id).orElseThrow();
        fornecedor = Fornecedor.mapToObj(dto);
        fornecedor.setId(id);
        fornecedor = fornecedorRepository.save(fornecedor);
        return Fornecedor.mapToDto(fornecedor);
    }

    public void delete(Integer id) {
        fornecedorRepository.deleteById(id);
    }
}
