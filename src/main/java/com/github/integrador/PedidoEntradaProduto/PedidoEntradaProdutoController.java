package com.github.integrador.PedidoEntradaProduto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pedido-entrada-produto")
public class PedidoEntradaProdutoController {
    @Autowired
    private PedidoEntradaProdutoService pedidoEntradaProduto;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> getOne(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(pedidoEntradaProduto.getOne(id));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> getAll(
            @RequestParam int page,
            @RequestParam int count
    ) {
        return ResponseEntity.ok(pedidoEntradaProduto.getAll(page, count));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> post(@RequestBody PedidoEntradaProdutoPostDto dto) {
        return ResponseEntity.ok(pedidoEntradaProduto.post(dto));
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> patch(
            @PathVariable("id") Integer id,
            @RequestBody PedidoEntradaProdutoPostDto dto
    ) {
        return ResponseEntity.ok(pedidoEntradaProduto.patch(id, dto));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> delete(@PathVariable("id") Integer id) {
        pedidoEntradaProduto.delete(id);
        return ResponseEntity.ok().build();
    }
}