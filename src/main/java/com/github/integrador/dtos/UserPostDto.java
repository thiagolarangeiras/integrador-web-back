package com.github.integrador.dtos;

import lombok.Builder;
import java.util.Date;

@Builder
public record UserPostDto(
        String username,
        String email,
        String password
) { }