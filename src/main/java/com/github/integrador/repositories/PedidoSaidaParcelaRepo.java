package com.github.integrador.repositories;

import com.github.integrador.models.PedidoSaidaParcela;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PedidoSaidaParcelaRepo extends JpaRepository<PedidoSaidaParcela, Integer> {
    Optional<Page<PedidoSaidaParcela>> findById(Integer id, Pageable pageable);
}
