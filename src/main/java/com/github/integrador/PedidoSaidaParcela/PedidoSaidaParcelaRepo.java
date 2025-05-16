package com.github.integrador.PedidoSaidaParcela;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PedidoSaidaParcelaRepo extends JpaRepository<PedidoSaidaParcela, Integer> {
    Optional<Page<PedidoSaidaParcela>> findByIdPedidoSaida(Integer id, Pageable pageable);
    Optional<List<PedidoSaidaParcela>> findByIdPedidoSaida(Integer id);
}
