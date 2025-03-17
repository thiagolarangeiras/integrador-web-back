package com.github.integrador.auth;

import lombok.Builder;
import java.util.Date;

@Builder
public record UserPostDto(
        String username,
        String email,
        String password,
        String completeName,
        Date birthDate,
        Integer type
) { }