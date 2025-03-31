package com.github.integrador.auth;

import com.github.integrador.dtos.UserGetDto;
import com.github.integrador.dtos.UserPostDto;
import com.github.integrador.services.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired
    UsuarioService service;

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public LoginResponseDto login(@RequestBody @Valid LoginRequestDto authenticationDto) {
        return new LoginResponseDto(service.login(authenticationDto));
    }

    @PostMapping("/signin")
    @ResponseStatus(HttpStatus.CREATED)
    public UserGetDto register(@RequestBody @Valid UserPostDto dto) {
        return service.post(dto);
    }
}