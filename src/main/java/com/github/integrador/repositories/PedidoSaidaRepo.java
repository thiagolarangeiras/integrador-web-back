package com.github.integrador.repositories;

import com.github.integrador.models.PedidoSaida;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
public interface PedidoSaidaRepo extends JpaRepository<PedidoSaida, Integer> {
    Optional<Page<PedidoSaida>> findById(Integer id, Pageable pageable);
}