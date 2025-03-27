package com.github.integrador.repositories;

import com.github.integrador.models.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepo extends JpaRepository<Cliente, Integer> {
    Optional<Page<Cliente>> findById(Integer id, Pageable pageable);
}