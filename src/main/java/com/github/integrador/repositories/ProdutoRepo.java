package com.github.integrador.repositories;

import com.github.integrador.models.Produto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProdutoRepo extends JpaRepository<Produto, Integer> {
    Optional<Page<Produto>> findById(Integer id, Pageable pageable);
}

