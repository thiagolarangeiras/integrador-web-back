package com.github.integrador.repositories;

import com.github.integrador.models.Cliente;
import com.github.integrador.models.Marca;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MarcaRepository extends JpaRepository<Marca, Integer> {
    Optional<Page<Marca>> findById(Integer id, Pageable pageable);
}