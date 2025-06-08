package com.github.integrador.DadosEmpresa;

import com.github.integrador.Fornecedor.Fornecedor;
import com.github.integrador.Fornecedor.FornecedorGetDto;
import com.github.integrador.Fornecedor.FornecedorPostDto;
import com.github.integrador.Fornecedor.FornecedorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DadosEmpresaService {
    @Autowired private DadosEmpresaRepo repo;

    public List<FornecedorGetDto> getAll (int page, int count) {
        Pageable pageable = PageRequest.of(page, count);
        return repo.findAll(pageable)
                .stream()
                .map(Fornecedor::mapToDto)
                .collect(Collectors.toList());
    }

    public List<FornecedorGetDto> getAllFilter (Integer page, Integer count, String nome) {
        Pageable pageable = PageRequest.of(page, count);
        return repo.findByNomeContaining(nome, pageable)
                .orElseThrow()
                .stream()
                .map(Fornecedor::mapToDto)
                .toList();
    }

    public FornecedorGetDto getOne (Integer id) {
        Optional<Fornecedor> fornecedorOptional = fornecedorRepo.findById(id);
        if(fornecedorOptional.isPresent()){
            Fornecedor fornecedor  = fornecedorOptional.get();
            return Fornecedor.mapToDto(fornecedor);
        }
        return null;
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
