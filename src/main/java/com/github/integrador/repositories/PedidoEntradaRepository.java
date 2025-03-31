package com.github.integrador.repositories;

import com.github.integrador.models.PedidoEntrada;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PedidoEntradaRepository extends JpaRepository<PedidoEntrada, Integer> {
    Optional<Page<PedidoEntrada>> findById(Integer id, Pageable pageable);
}