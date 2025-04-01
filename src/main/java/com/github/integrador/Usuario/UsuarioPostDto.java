package com.github.integrador.Usuario;

import lombok.Builder;

@Builder
public record UsuarioPostDto(
        String username,
        String email,
        String password
) { }