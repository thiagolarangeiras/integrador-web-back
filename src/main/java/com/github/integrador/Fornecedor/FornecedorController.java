package com.github.integrador.Fornecedor;

import com.github.integrador.Marca.MarcaGetDto;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fornecedor")
public class FornecedorController {
    @Autowired
    private FornecedorService fornecedorService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> getOne(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(fornecedorService.getOne(id));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<FornecedorGetDto>> getAll(
            @RequestParam Integer page,
            @RequestParam Integer count,
            @Nullable @RequestParam String nome
    ) {
        if (nome == null || nome.isBlank())
            return ResponseEntity.ok(fornecedorService.getAll(page, count));
        return ResponseEntity.ok(fornecedorService.getAllFilter(page, count, nome));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> post(@RequestBody FornecedorPostDto dto) {
        return ResponseEntity.ok(fornecedorService.post(dto));
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> patch(
            @PathVariable("id") Integer id,
            @RequestBody FornecedorPostDto dto
    ) {
        return ResponseEntity.ok(fornecedorService.patch(id, dto));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> delete(@PathVariable("id") Integer id) {
        fornecedorService.delete(id);
        return ResponseEntity.ok().build();
    }
}
