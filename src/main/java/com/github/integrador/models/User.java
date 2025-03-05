package com.github.integrador.models;

import com.controlis.salus.dtos.UserInsertDto;
import com.controlis.salus.dtos.UserReturnDto;
import com.controlis.salus.models.enums.UserType;
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
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(unique = true)
    private String username;

    @Column(unique = true)
    private String email;
    private String password;
    private String completeName;
    private Date birthDate;
    private UserType type;
    private Set<String> roles = new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream().map((role -> new SimpleGrantedAuthority(role))).toList();
    }

    //Mappers
    public static UserReturnDto convertEntityToDto(User user) {
        return new UserReturnDto(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getCompleteName(),
                user.getBirthDate(),
                user.getType()
        );
    }

    public static User convertDtoToEntity(UserInsertDto dto) {
        return User.builder()
                .username(dto.username())
                .email(dto.email())
                .password(new BCryptPasswordEncoder().encode(dto.password()))
                .completeName(dto.completeName())
                .birthDate(dto.birthDate())
                .type(dto.type())
                .roles(new HashSet<String>())
                .build();
    }
}