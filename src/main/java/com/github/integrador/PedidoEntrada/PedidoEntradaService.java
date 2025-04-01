package com.github.integrador.PedidoEntrada;


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
    private PedidoEntradaRepo repo;

    public List<PedidoEntradaGetDto> getAll (int page, int count) {
        Pageable pageable = PageRequest.of(page, count);
        return repo.findAll(pageable)
                .stream()
                .map(PedidoEntrada::mapToDto)
                .collect(Collectors.toList());
    }

    public PedidoEntradaGetDto getOne (Integer id) {
        Optional<PedidoEntrada> pedidoentradaOptional = repo.findById(id);
        PedidoEntrada pedidoentrada = pedidoentradaOptional.orElseThrow();;
        return PedidoEntrada.mapToDto(pedidoentrada);
    }

    public PedidoEntradaGetDto post(PedidoEntradaPostDto dto) {
        PedidoEntrada pedidoentrada = PedidoEntrada.mapToObj(dto);
        pedidoentrada = repo.save(pedidoentrada);
        return PedidoEntrada.mapToDto(pedidoentrada);
    }

    public PedidoEntradaGetDto patch(Integer id, PedidoEntradaPostDto dto) {
        PedidoEntrada pedidoentrada  = repo.findById(id).orElseThrow();
        pedidoentrada = PedidoEntrada.mapToObj(dto);
        pedidoentrada.setId(id);
        pedidoentrada = repo.save(pedidoentrada);
        return PedidoEntrada.mapToDto(pedidoentrada);
    }

    public void delete(Integer id) {
        repo.deleteById(id);
    }
}
