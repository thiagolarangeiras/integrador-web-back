package com.github.integrador.Usuario;

import lombok.Builder;

import java.util.List;

@Builder
public record UsuarioPostDto(
        String username,
        String email,
        String password,
        Cargo cargo
) { }