package com.github.integrador.PedidoSaida;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PedidoSaidaService {
    @Autowired
    private PedidoSaidaRepo repo;

    public List<PedidoSaidaGetDto> getAll (int page, int count) {
        Pageable pageable = PageRequest.of(page, count);
        return repo.findAll(pageable)
                .stream()
                .map(PedidoSaida::mapToDto)
                .collect(Collectors.toList());
    }

    public PedidoSaidaGetDto getOne (Integer id) {
        Optional<PedidoSaida> optional = repo.findById(id);
        PedidoSaida obj = optional.orElseThrow();;
        return PedidoSaida.mapToDto(obj);
    }

    public PedidoSaidaGetDto post(PedidoSaidaPostDto dto) {
        PedidoSaida obj = PedidoSaida.mapToObj(dto);
        obj = repo.save(obj);
        return PedidoSaida.mapToDto(obj);
    }

    public PedidoSaidaGetDto patch(Integer id, PedidoSaidaPostDto dto) {
        PedidoSaida obj = repo.findById(id).orElseThrow();
        obj = PedidoSaida.mapToObj(dto);
        obj.setId(id);
        obj = repo.save(obj);
        return PedidoSaida.mapToDto(obj);
    }

    public void delete(Integer id) {
        repo.deleteById(id);
    }
}