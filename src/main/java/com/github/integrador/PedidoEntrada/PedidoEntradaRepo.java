package com.github.integrador.PedidoEntrada;

import com.github.integrador.Cliente.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PedidoEntradaRepo extends JpaRepository<PedidoEntrada, Integer> {
    Optional<Page<PedidoEntrada>> findById(Integer id, Pageable pageable);
    List<PedidoEntrada> findAllByOrderByDataVigenciaAsc(Pageable pageable);

}