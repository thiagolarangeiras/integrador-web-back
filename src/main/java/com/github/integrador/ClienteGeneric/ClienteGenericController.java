package com.github.integrador.ClienteGeneric;

import com.github.integrador.Cliente.*;
import com.github.integrador.infra.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cliente-teste")
public class ClienteGenericController {
    @Autowired
    private GenericService<
            Cliente,
            ClienteGetDto,
            ClientePostDto,
            ClienteRepo,
            ClienteMapper
            > clienteService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> getOne(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(clienteService.getOne(id));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> getAll(
            @RequestParam int page,
            @RequestParam int count
    ) {
        return ResponseEntity.ok(clienteService.getAll(page, count));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> post(@RequestBody ClientePostDto dto) {
        return ResponseEntity.ok(clienteService.post(dto));
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> patch(
            @PathVariable("id") Integer id,
            @RequestBody ClientePostDto dto
    ) {
        return ResponseEntity.ok(clienteService.patch(id, dto));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> delete(@PathVariable("id") Integer id) {
        clienteService.delete(id);
        return ResponseEntity.ok().build();
    }
}
