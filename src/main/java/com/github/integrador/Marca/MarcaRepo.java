package com.github.integrador.Marca;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MarcaRepo extends JpaRepository<Marca, Integer> {
    Optional<Page<Marca>> findById(Integer id, Pageable pageable);
    Optional<Page<Marca>> findByNomeContaining(String nome, Pageable pageable);
}