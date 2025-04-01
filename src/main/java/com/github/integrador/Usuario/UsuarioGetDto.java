package com.github.integrador.Usuario;

import lombok.Builder;

@Builder
public record UsuarioGetDto(
        Integer id,
        String username,
        String email
) { }