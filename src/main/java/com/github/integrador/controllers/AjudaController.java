package com.github.integrador.controllers;

import com.github.integrador.models.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ajuda")
public class AjudaController {
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public String ajuda() {
        return "Servidor OK";
    }
}
