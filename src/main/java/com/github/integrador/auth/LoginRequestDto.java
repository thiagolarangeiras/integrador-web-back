package com.github.integrador.auth;

public record LoginRequestDto (
        String username,
        String password
) { }