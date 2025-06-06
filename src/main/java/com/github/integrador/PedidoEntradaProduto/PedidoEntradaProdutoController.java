package com.github.integrador.PedidoEntradaProduto;

import com.github.integrador.PedidoSaidaProduto.PedidoSaidaProdutoGetDto;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedido-entrada-produto")
public class PedidoEntradaProdutoController {
    @Autowired private PedidoEntradaProdutoService service;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<PedidoEntradaProdutoGetDto> getOne(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(service.getOne(id));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<PedidoEntradaProdutoGetDto>> getAll(
            @RequestParam int page,
            @RequestParam int count,
            @Nullable @RequestParam Integer idPedido
    ) {
        List<PedidoEntradaProdutoGetDto> dto;
        if(idPedido == null){
            dto = service.getAll(page, count);
        } else {
            dto = service.getAllIdPedido(page, count, idPedido);
        }
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<PedidoEntradaProdutoGetDto> post(@RequestBody PedidoEntradaProdutoPostDto dto) {
        return ResponseEntity.ok(service.post(dto));
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<PedidoEntradaProdutoGetDto> patch(@PathVariable("id") Integer id, @RequestBody PedidoEntradaProdutoPostDto dto) {
        return ResponseEntity.ok(service.patch(id, dto));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<PedidoEntradaProdutoGetDto> delete(@PathVariable("id") Integer id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }
}