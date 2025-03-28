package com.github.integrador.services;

import com.github.integrador.dtos.ClienteGetDto;
import com.github.integrador.dtos.ClientePostDto;
import com.github.integrador.dtos.MarcaGetDto;
import com.github.integrador.dtos.MarcaPostDto;
import com.github.integrador.models.Cliente;
import com.github.integrador.models.Marca;
import com.github.integrador.repositories.MarcaRepository;
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
    private MarcaRepository marcaRepository;

    public List<MarcaGetDto> getAll (int page, int count) {
        Pageable pageable = PageRequest.of(page, count);
        return marcaRepository.findAll(pageable)
                .stream()
                .map(Marca::mapToDto)
                .collect(Collectors.toList());
    }

    public MarcaGetDto getOne (Integer id) {
        Optional<Marca> marcaOptional = marcaRepository.findById(id);
        Marca marca = marcaOptional.orElseThrow();;
        return Marca.mapToDto(marca);
    }

    public MarcaGetDto post(MarcaPostDto dto) {
        Marca marca = Marca.mapToObj(dto);
        marca = marcaRepository.save(marca);
        return Marca.mapToDto(marca);
    }

    public MarcaGetDto patch(Integer id, MarcaPostDto dto) {
        Marca marca = marcaRepository.findById(id).orElseThrow();
        marca = Marca.mapToObj(dto);
        marca.setId(id);
        marca = marcaRepository.save(marca);
        return Marca.mapToDto(marca);
    }

}
