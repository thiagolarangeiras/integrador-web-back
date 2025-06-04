package com.github.integrador.PedidoEntradaParcela;

import com.github.integrador.PedidoEntradaProduto.PedidoEntradaProduto;
import com.github.integrador.PedidoEntradaProduto.PedidoEntradaProdutoGetDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PedidoEntradaParcelaService {
    @Autowired
    private PedidoEntradaParcelaRepo repo;

    public List<PedidoEntradaParcelaGetDto> getAll (int page, int count) {
        Pageable pageable = PageRequest.of(page, count);
        return repo.findAll(pageable)
                .stream()
                .map(PedidoEntradaParcela::mapToDto)
                .collect(Collectors.toList());
    }

    public PedidoEntradaParcelaGetDto getOne (Integer id) {
        Optional<PedidoEntradaParcela> opcional = repo.findById(id);
        PedidoEntradaParcela obj = opcional.orElseThrow();;
        return PedidoEntradaParcela.mapToDto(obj);
    }

    public PedidoEntradaParcelaGetDto post(PedidoEntradaParcelaPostDto dto) {
        PedidoEntradaParcela pedidoentradaproduto = PedidoEntradaParcela.mapToObj(dto);
        pedidoentradaproduto = repo.save(pedidoentradaproduto);
        return PedidoEntradaParcela.mapToDto(pedidoentradaproduto);
    }

    public PedidoEntradaParcelaGetDto patch(Integer id, PedidoEntradaParcelaPostDto dto) {
        PedidoEntradaParcela pedidoentradaproduto = repo.findById(id).orElseThrow();
        pedidoentradaproduto = PedidoEntradaParcela.mapToObj(dto);
        pedidoentradaproduto.setId(id);
        pedidoentradaproduto = repo.save(pedidoentradaproduto);
        return PedidoEntradaParcela.mapToDto(pedidoentradaproduto);
    }

    public void delete(Integer id) {
        repo.deleteById(id);
    }

    public List<PedidoEntradaParcelaGetDto> getByIdPedido(Integer id){
        Optional<List<PedidoEntradaParcela>> parcelaOptional = repo.findByIdPedidoEntrada(id);
        if(parcelaOptional.isPresent()){
            return parcelaOptional.get()
                    .stream()
                    .map(PedidoEntradaParcela::mapToDto)
                    .toList();
        }
        return null;
    }
}

