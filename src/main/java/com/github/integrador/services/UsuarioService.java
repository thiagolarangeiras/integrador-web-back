package com.github.integrador.services;

import com.github.integrador.auth.LoginRequestDto;
import com.github.integrador.auth.TokenService;
import com.github.integrador.dtos.UserGetDto;
import com.github.integrador.dtos.UserPostDto;
import com.github.integrador.models.Usuario;
import com.github.integrador.repositories.UsuarioRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UsuarioRepo usuarioRepo;
    @Autowired
    TokenService tokenService;

    @Transactional(readOnly = true)
    public String login(LoginRequestDto dto) throws AuthenticationException {
        Authentication usernamePassword = new UsernamePasswordAuthenticationToken(
                dto.username(),
                dto.password()
        );
        Authentication auth = authenticationManager.authenticate(usernamePassword);
        Usuario usuario = (Usuario) auth.getPrincipal();
        return tokenService.generateToken(usuario.getUsername());
    }

    @Transactional
    public UserGetDto post(UserPostDto dto) {
        Usuario usuario = Usuario.convertDtoToEntity(dto);
        usuario = usuarioRepo.save(usuario);
        return Usuario.convertEntityToDto(usuario);
    }

    public List<UserGetDto> getAll(int page, int count) {
        Pageable pageable = PageRequest.of(page, count);
        return usuarioRepo.findAll(pageable).stream()
                //.filter(user -> user.getType() == 1)
                .map(Usuario::convertEntityToDto)
                .collect(Collectors.toList());
    }

    public UserGetDto getOne(Integer id) {
        return usuarioRepo.findById(id)
                .map(Usuario::convertEntityToDto)
                .orElse(null);
    }

    public UserGetDto patch(Integer id, UserPostDto dto) {
        Usuario usuario = Usuario.convertDtoToEntity(dto);
        usuario.setId(id);
        usuario = usuarioRepo.save(usuario);
        return Usuario.convertEntityToDto(usuario);
    }

    public void delete(Integer id) {
        usuarioRepo.deleteById(id);
    }
}