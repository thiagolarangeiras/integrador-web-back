package com.github.integrador.Usuario;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
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

    //Mappers
    public static UsuarioGetDto convertEntityToDto(Usuario usuario) {
        return new UsuarioGetDto(
            usuario.getId(),
            usuario.getUsername(),
            usuario.getEmail()
        );
    }

    public static Usuario convertDtoToEntity(UsuarioPostDto dto) {
        Usuario obj = new Usuario();
        obj.username = dto.username();
        obj.email    = dto.email();
        obj.password = new BCryptPasswordEncoder().encode(dto.password());
        return obj;
    }
}