package com.github.integrador.models;

import com.github.integrador.dtos.UserGetDto;
import com.github.integrador.dtos.UserPostDto;
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
public class Usuario implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private String username;

    @Column(unique = true)
    private String email;

    private String password;
    private Set<String> roles = new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream().map((role -> new SimpleGrantedAuthority(role))).toList();
    }

    //Mappers
    public static UserGetDto convertEntityToDto(Usuario usuario) {
        return new UserGetDto(
                usuario.getId(),
                usuario.getUsername(),
                usuario.getEmail()
        );
    }

    public static Usuario convertDtoToEntity(UserPostDto dto) {
        return Usuario.builder()
                .username(dto.username())
                .email(dto.email())
                .password(new BCryptPasswordEncoder().encode(dto.password()))
                .roles(new HashSet<String>())
                .build();
    }
}