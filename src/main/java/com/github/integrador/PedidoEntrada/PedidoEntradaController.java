package com.github.integrador.PedidoEntrada;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedido-entrada")
public class PedidoEntradaController {
    @Autowired
    private PedidoEntradaService pedidoEntradaService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> getOne(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(pedidoEntradaService.getOne(id));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> getAll(
            @RequestParam int page,
            @RequestParam int count
    ) {
//        return ResponseEntity.ok(pedidoEntradaService.getAll(page, count));
        return ResponseEntity.ok("");
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> post(@RequestBody PedidoEntradaPostDto dto) {
        return ResponseEntity.ok(pedidoEntradaService.post(dto));
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> patch(
            @PathVariable("id") Integer id,
            @RequestBody PedidoEntradaPostDto dto
    ) {
        return ResponseEntity.ok(pedidoEntradaService.patch(id, dto));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> delete(@PathVariable("id") Integer id) {
        pedidoEntradaService.delete(id);
        return ResponseEntity.ok().build();
    }
}
