package com.github.integrador.PedidoSaidaProduto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PedidoSaidaProdutoRepo extends JpaRepository<PedidoSaidaProduto, Integer> {
    Optional<Page<PedidoSaidaProduto>> findByIdPedidoSaida(Integer id, Pageable pageable);
    Optional<List<PedidoSaidaProduto>> findByIdPedidoSaida(Integer id);

    @Query(value = "select * from pedido_saida_produto where id_pedido_saida = ?1", nativeQuery = true)
    Page<PedidoSaidaProduto> findByPedido(Integer idPedidoSaida, Pageable pageable);
}