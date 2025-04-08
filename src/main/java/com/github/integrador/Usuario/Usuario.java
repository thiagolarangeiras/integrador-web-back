package com.github.integrador.Usuario;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.*;

@Entity
@Table(name = "usuario")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private String username;

    @Column(unique = true)
    private String email;

    private String password;
    private List<Cargo> cargo;

    //Mappers
    public static UsuarioGetDto convertEntityToDto(Usuario usuario) {
        return new UsuarioGetDto(
            usuario.getId(),
            usuario.getUsername(),
            usuario.getEmail(),
            usuario.getCargo()
        );
    }

    public static Usuario convertDtoToEntity(UsuarioPostDto dto) {
        Usuario u = new Usuario();
        u.setUsername(dto.username());
        u.setEmail(dto.email());
        u.setPassword(new BCryptPasswordEncoder().encode(dto.password()));
        u.setCargo(dto.cargo());
        return u;
    }
}