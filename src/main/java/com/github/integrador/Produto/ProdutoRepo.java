package com.github.integrador.Produto;

import com.github.integrador.Marca.Marca;
import com.github.integrador.PedidoSaidaProduto.PedidoSaidaProduto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ProdutoRepo extends JpaRepository<Produto, Integer> {
    Optional<Page<Produto>> findById(Integer id, Pageable pageable);
    Optional<Page<Produto>> findByNomeContaining(String nome, Pageable pageable);
}

