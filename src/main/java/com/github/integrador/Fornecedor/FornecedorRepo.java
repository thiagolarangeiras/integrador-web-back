package com.github.integrador.Fornecedor;

import com.github.integrador.Cliente.Cliente;
import com.github.integrador.Marca.Marca;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FornecedorRepo extends JpaRepository<Fornecedor, Integer> {
    Optional<Page<Fornecedor>> findById(Integer id, Pageable pageable);
    Optional<Page<Fornecedor>> findByNomeContaining(String nome, Pageable pageable);
    Optional<Page<Fornecedor>> findByNomeContainingIgnoreCase(String nome, Pageable pageable);
    Optional<Page<Fornecedor>> findByCpfCnpjContainingIgnoreCase(String documento, Pageable pageable);
}
