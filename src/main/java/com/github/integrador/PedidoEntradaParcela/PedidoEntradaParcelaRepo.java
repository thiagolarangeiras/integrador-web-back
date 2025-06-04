package com.github.integrador.PedidoEntradaParcela;

import com.github.integrador.PedidoEntradaProduto.PedidoEntradaProduto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PedidoEntradaParcelaRepo extends JpaRepository<PedidoEntradaParcela, Integer> {
    Optional<Page<PedidoEntradaParcela>> findById(Integer id, Pageable pageable);
    Optional<List<PedidoEntradaParcela>> findByIdPedidoEntrada(Integer id);
}
