package com.github.integrador.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ajuda")
public class AjudaController {
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public String ajuda() {
        return "Servidor OK";
    }
}