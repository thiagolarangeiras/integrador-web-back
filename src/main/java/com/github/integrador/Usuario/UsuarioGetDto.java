package com.github.integrador.Usuario;

import lombok.Builder;

import java.util.List;

@Builder
public record UsuarioGetDto(
        Integer id,
        String username,
        String email,
        Cargo cargo
) { }