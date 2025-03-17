package com.github.integrador.auth;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

record LoginResponseDto(
        String token
) { }

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired
    UserService authenticationService;

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public LoginResponseDto login(@RequestBody @Valid LoginRequestDto authenticationDto) {
        return new LoginResponseDto(authenticationService.login(authenticationDto));
    }

    @PostMapping("/signin")
    @ResponseStatus(HttpStatus.CREATED)
    public UserGetDto register(@RequestBody @Valid UserPostDto dto) {
        return authenticationService.saveUser(dto);
    }
}