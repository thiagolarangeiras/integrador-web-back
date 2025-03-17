package com.github.integrador.auth;

import java.util.Date;

public record UserGetDto(
        Integer id,
        String username,
        String email,
        String completeName,
        Date birthDate,
        Integer type
) { }