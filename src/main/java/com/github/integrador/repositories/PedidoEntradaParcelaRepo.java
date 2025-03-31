package com.github.integrador.repositories;

import com.github.integrador.models.PedidoEntradaParcela;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PedidoEntradaParcelaRepo extends JpaRepository<PedidoEntradaParcela, Integer> {
    Optional<Page<PedidoEntradaParcela>> findById(Integer id, Pageable pageable);
}
