package com.github.integrador.PedidoEntradaProduto;

import com.github.integrador.PedidoSaidaProduto.PedidoSaidaProduto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PedidoEntradaProdutoRepo extends JpaRepository<PedidoEntradaProduto, Integer> {
    Optional<Page<PedidoEntradaProduto>> findById(Integer id, Pageable pageable);
    Optional<List<PedidoEntradaProduto>> findByIdPedidoEntrada(Integer id);

    @Query(value = "select * from pedido_entrada_produto where id_pedido_entrada = ?1", nativeQuery = true)
    Page<PedidoEntradaProduto> findByPedido(Integer idPedidoEntrada, Pageable pageable);
}
