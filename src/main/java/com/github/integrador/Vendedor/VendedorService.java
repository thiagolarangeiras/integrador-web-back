package com.github.integrador.Vendedor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VendedorService {
    @Autowired
    private VendedorRepo vendedorRepo;

    public List<VendedorGetDto> getAll (int page, int count) {
        Pageable pageable = PageRequest.of(page, count);
        return vendedorRepo.findAll(pageable)
                .stream()
                .map(Vendedor::mapToDto)
                .collect(Collectors.toList());
    }

    public VendedorGetDto getOne (Integer id) {
        Vendedor vendedor = vendedorRepo.findById(id).orElse(null);
        return Vendedor.mapToDto(vendedor);
    }

    public VendedorGetDto post(VendedorPostDto dto) {
        Vendedor vendedor = Vendedor.mapToObj(dto);
        vendedor = vendedorRepo.save(vendedor);
        return Vendedor.mapToDto(vendedor);
    }

    public VendedorGetDto patch(Integer id, VendedorPostDto dto) {
        Vendedor vendedor = vendedorRepo.findById(id).orElseThrow();
        vendedor = vendedor.mapToObj(dto);
        vendedor.setId(id);
        vendedor = vendedorRepo.save(vendedor);
        return Vendedor.mapToDto(vendedor);
    }

    public void delete(Integer id) {
        vendedorRepo.deleteById(id);
    }
}