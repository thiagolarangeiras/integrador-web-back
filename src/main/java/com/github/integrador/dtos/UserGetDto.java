package com.github.integrador.dtos;

import java.util.Date;

public record UserGetDto(
        Integer id,
        String username,
        String email
) { }