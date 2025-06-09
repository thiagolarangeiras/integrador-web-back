package com.github.integrador.DadosEmpresa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dados-empresa")
public class DadosEmpresaController{
    @Autowired private DadosEmpresaService service;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<DadosEmpresaGetDto> get() {
        return ResponseEntity.ok(service.get());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<DadosEmpresaGetDto> post(@RequestBody DadosEmpresaPostDto dto) {
        return ResponseEntity.ok(service.post(dto));
    }

    @PatchMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<DadosEmpresaGetDto> patch(@RequestBody DadosEmpresaPostDto dto) {
        return ResponseEntity.ok(service.patch(dto));
    }
}
