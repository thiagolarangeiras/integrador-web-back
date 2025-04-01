package com.github.integrador.Usuario;

import com.github.integrador.auth.LoginRequestDto;
import com.github.integrador.auth.TokenService;
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
    public UsuarioGetDto post(UsuarioPostDto dto) {
        Usuario usuario = Usuario.convertDtoToEntity(dto);
        usuario = usuarioRepo.save(usuario);
        return Usuario.convertEntityToDto(usuario);
    }

    public List<UsuarioGetDto> getAll(int page, int count) {
        Pageable pageable = PageRequest.of(page, count);
        return usuarioRepo.findAll(pageable).stream()
                //.filter(user -> user.getType() == 1)
                .map(Usuario::convertEntityToDto)
                .collect(Collectors.toList());
    }

    public UsuarioGetDto getOne(Integer id) {
        return usuarioRepo.findById(id)
                .map(Usuario::convertEntityToDto)
                .orElse(null);
    }

    public UsuarioGetDto patch(Integer id, UsuarioPostDto dto) {
        Usuario usuario = Usuario.convertDtoToEntity(dto);
        usuario.setId(id);
        usuario = usuarioRepo.save(usuario);
        return Usuario.convertEntityToDto(usuario);
    }

    public void delete(Integer id) {
        usuarioRepo.deleteById(id);
    }
}