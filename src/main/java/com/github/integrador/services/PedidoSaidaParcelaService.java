package com.github.integrador.services;

import com.github.integrador.dtos.PedidoSaidaParcelaGetDto;
import com.github.integrador.dtos.PedidoSaidaParcelaPostDto;
import com.github.integrador.models.PedidoSaidaParcela;
import com.github.integrador.repositories.PedidoSaidaParcelaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PedidoSaidaParcelaService {
    @Autowired
    private PedidoSaidaParcelaRepo repo;

    public List<PedidoSaidaParcelaGetDto> getAll (int page, int count) {
        Pageable pageable = PageRequest.of(page, count);
        return repo.findAll(pageable)
                .stream()
                .map(PedidoSaidaParcela::mapToDto)
                .collect(Collectors.toList());
    }

    public PedidoSaidaParcelaGetDto getOne (Integer id) {
        Optional<PedidoSaidaParcela> optional = repo.findById(id);
        PedidoSaidaParcela obj = optional.orElseThrow();;
        return PedidoSaidaParcela.mapToDto(obj);
    }

    public PedidoSaidaParcelaGetDto post(PedidoSaidaParcelaPostDto dto) {
        PedidoSaidaParcela obj = PedidoSaidaParcela.mapToObj(dto);
        obj = repo.save(obj);
        return PedidoSaidaParcela.mapToDto(obj);
    }

    public PedidoSaidaParcelaGetDto patch(Integer id, PedidoSaidaParcelaPostDto dto) {
        PedidoSaidaParcela obj = repo.findById(id).orElseThrow();
        obj = PedidoSaidaParcela.mapToObj(dto);
        obj.setId(id);
        obj = repo.save(obj);
        return PedidoSaidaParcela.mapToDto(obj);
    }

    public void delete(Integer id) {
        repo.deleteById(id);
    }
}

