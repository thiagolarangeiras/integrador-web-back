package com.github.integrador.services;


import com.github.integrador.dtos.PedidoEntradaGetDto;
import com.github.integrador.dtos.PedidoEntradaPostDto;
import com.github.integrador.models.PedidoEntrada;
import com.github.integrador.repositories.PedidoEntradaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PedidoEntradaService {
    @Autowired
    private PedidoEntradaRepository pedidoentradaRepo;

    public List<PedidoEntradaGetDto> getAll (int page, int count) {
        Pageable pageable = PageRequest.of(page, count);
        return pedidoentradaRepo.findAll(pageable)
                .stream()
                .map(PedidoEntrada::mapToDto)
                .collect(Collectors.toList());
    }

    /*Pageable pageable = PageRequest.of(page, count);
        return pedidoentradaRepo.findById(id, pageable)
            .orElseThrow()
            .stream()
            .map(PedidoEntrada::mapToDto)
            .collect(Collectors.toList());
     */

    public PedidoEntradaGetDto getOne (Integer id) {
        Optional<PedidoEntrada> pedidoentradaOptional = pedidoentradaRepo.findById(id);
        if (!pedidoentradaOptional.isPresent()){
            //
        }

        PedidoEntrada pedidoentrada = pedidoentradaOptional.orElseThrow();;
        return PedidoEntrada.mapToDto(pedidoentrada);
    }

    public PedidoEntradaGetDto post(PedidoEntradaPostDto dto) {
        PedidoEntrada pedidoentrada = PedidoEntrada.mapToObj(dto);
        pedidoentrada = pedidoentradaRepo.save(pedidoentrada);
        return PedidoEntrada.mapToDto(pedidoentrada);
    }

    public PedidoEntradaGetDto patch(Integer id, PedidoEntradaPostDto dto) {
        PedidoEntrada pedidoentrada  = pedidoentradaRepo.findById(id).orElseThrow();
        pedidoentrada = PedidoEntrada.mapToObj(dto);
        pedidoentrada.setId(id);
        pedidoentrada = pedidoentradaRepo.save(pedidoentrada);
        return PedidoEntrada.mapToDto(pedidoentrada);
    }

    public void delete(Integer id) {
        pedidoentradaRepo.deleteById(id);
    }
}
