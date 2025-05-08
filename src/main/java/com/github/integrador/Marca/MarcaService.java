package com.github.integrador.Marca;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MarcaService {
    @Autowired
    private MarcaRepo marcaRepo;

    public List<MarcaGetDto> getAll (Integer page, Integer count) {
        Pageable pageable = PageRequest.of(page, count);
        return marcaRepo.findAll(pageable)
                .stream()
                .map(Marca::mapToDto)
                .collect(Collectors.toList());
    }

    public List<MarcaGetDto> getAllFilter (Integer page, Integer count, String nome) {
        Pageable pageable = PageRequest.of(page, count);
        return marcaRepo.findByNomeContaining(nome, pageable)
                .orElseThrow()
                .stream()
                .map(Marca::mapToDto)
                .toList();
    }

    public MarcaGetDto getOne (Integer id) {
        Optional<Marca> marcaOptional = marcaRepo.findById(id);
        if(marcaOptional.isPresent()){
            Marca marca = marcaOptional.get();
            return Marca.mapToDto(marca);
        }
        return null;
    }

    public MarcaGetDto post(MarcaPostDto dto) {
        Marca marca = Marca.mapToObj(dto);
        marca = marcaRepo.save(marca);
        return Marca.mapToDto(marca);
    }

    public MarcaGetDto patch(Integer id, MarcaPostDto dto) {
        Marca marca = marcaRepo.findById(id).orElseThrow();
        marca = Marca.mapToObj(dto);
        marca.setId(id);
        marca = marcaRepo.save(marca);
        return Marca.mapToDto(marca);
    }

    public void delete(Integer id) {
        marcaRepo.deleteById(id);
    }
}
