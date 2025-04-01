package com.github.integrador.Vendedor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VendedorRepo extends JpaRepository<Vendedor, Integer> {
    Optional<Page<Vendedor>> findById(Integer id, Pageable pageable);
}