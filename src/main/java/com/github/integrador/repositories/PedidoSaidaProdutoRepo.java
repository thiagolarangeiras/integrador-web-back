package com.github.integrador.repositories;

import com.github.integrador.models.PedidoSaidaProduto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PedidoSaidaProdutoRepo extends JpaRepository<PedidoSaidaProduto, Integer> {
    Optional<Page<PedidoSaidaProduto>> findById(Integer id, Pageable pageable);
}