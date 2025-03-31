package com.github.integrador.dtos;

public record UserGetDto(
        Integer id,
        String username,
        String email
) { }