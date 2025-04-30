package com.github.integrador.Produto;

import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produto")
public class ProdutoController {
    @Autowired
    private ProdutoService produtoService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> getOne(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(produtoService.getOne(id));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<ProdutoGetDto>> getAll(
            @RequestParam Integer page,
            @RequestParam Integer count,
            @Nullable @RequestParam String nome
    ) {
        if (nome == null || nome.isBlank())
            return ResponseEntity.ok(produtoService.getAll(page, count));
        return ResponseEntity.ok(produtoService.getAllFilter(page, count, nome));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> post(@RequestBody ProdutoPostDto dto) {
        return ResponseEntity.ok(produtoService.post(dto));
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> patch(
            @PathVariable("id") Integer id,
            @RequestBody ProdutoPostDto dto
    ) {
        return ResponseEntity.ok(produtoService.patch(id, dto));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> delete(@PathVariable("id") Integer id) {
        produtoService.delete(id);
        return ResponseEntity.ok().build();
    }
}
