package com.github.integrador.repositories;

import com.github.integrador.models.Fornecedor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FornecedorRepo extends JpaRepository<Fornecedor, Integer> {
    Optional<Page<Fornecedor>> findById(Integer id, Pageable pageable);
}
