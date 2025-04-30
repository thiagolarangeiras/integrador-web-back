package com.github.integrador.Marca;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/marca")
public class MarcaController {
    @Autowired
    private MarcaService marcaService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> getOne(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(marcaService.getOne(id));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<MarcaGetDto>> getAll(
            @RequestParam Integer page,
            @RequestParam Integer count,
            @Nullable @RequestParam String nome
    ) {
        if (nome == null || nome.isBlank())
            return ResponseEntity.ok(marcaService.getAll(page, count));
        return ResponseEntity.ok(marcaService.getAllFilter(page, count, nome));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> post(@RequestBody MarcaPostDto dto) {
        return ResponseEntity.ok(marcaService.post(dto));
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> patch(
            @PathVariable("id") Integer id,
            @RequestBody MarcaPostDto dto
    ) {
        return ResponseEntity.ok(marcaService.patch(id, dto));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> delete(@PathVariable("id") Integer id) {
        marcaService.delete(id);
        return ResponseEntity.ok().build();
    }
}
