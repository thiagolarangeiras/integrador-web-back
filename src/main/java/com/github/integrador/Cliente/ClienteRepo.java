package com.github.integrador.Cliente;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepo extends JpaRepository<Cliente, Integer> {
    Optional<Page<Cliente>> findById(Integer id, Pageable pageable);
    Optional<Page<Cliente>> findByNomePessoaContainingIgnoreCase(String nomePessoa, Pageable pageable);
    Optional<Page<Cliente>> findByNomeEmpresaContainingIgnoreCase(String nomeEmpresa, Pageable pageable);
    Optional<Page<Cliente>> findByNomeFantasiaContainingIgnoreCase(String nomeFantasia, Pageable pageable);
    Optional<Page<Cliente>> findByDescricaoContainingIgnoreCase(String descricao, Pageable pageable);
}