package com.github.integrador.repositories;

import com.github.integrador.models.PedidoEntradaProduto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PedidoEntradaProdutoRepo extends JpaRepository<PedidoEntradaProduto, Integer> {
    Optional<Page<PedidoEntradaProduto>> findById(Integer id, Pageable pageable);
}
