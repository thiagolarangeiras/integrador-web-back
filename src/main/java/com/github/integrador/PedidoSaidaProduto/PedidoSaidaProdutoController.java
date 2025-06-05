package com.github.integrador.PedidoSaidaProduto;

import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedido-saida-produto")
public class PedidoSaidaProdutoController {
    @Autowired
    private PedidoSaidaProdutoService service;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> getOne(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(service.getOne(id));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<PedidoSaidaProdutoGetDto>> getAll(
            @RequestParam Integer page,
            @RequestParam Integer count,
            @Nullable @RequestParam Integer idPedido
    ) {
        List<PedidoSaidaProdutoGetDto> dto;
        if(idPedido == null){
            dto = service.getAll(page, count);
        } else {
            dto = service.getAllIdPedido(page, count, idPedido);
        }
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> post(@RequestBody PedidoSaidaProdutoPostDto dto) {
        return ResponseEntity.ok(service.post(dto));
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> patch(
            @PathVariable("id") Integer id,
            @RequestBody PedidoSaidaProdutoPostDto dto
    ) {
        return ResponseEntity.ok(service.patch(id, dto));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> delete(@PathVariable("id") Integer id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }
}
