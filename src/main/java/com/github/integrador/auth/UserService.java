package com.github.integrador.services;

import com.github.integrador.auth.LoginRequestDto;
import com.github.integrador.auth.TokenService;
import com.github.integrador.dtos.UserGetDto;
import com.github.integrador.dtos.UserPostDto;
import com.github.integrador.models.User;
import com.github.integrador.repositories.UserRepository;
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
public class UserService {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserRepository userRepository;
    @Autowired
    TokenService tokenService;

    @Transactional(readOnly = true)
    public String login(LoginRequestDto dto) throws AuthenticationException {
        Authentication usernamePassword = new UsernamePasswordAuthenticationToken(
                dto.username(),
                dto.password()
        );
        Authentication auth = authenticationManager.authenticate(usernamePassword);
        User user = (User) auth.getPrincipal();
        return tokenService.generateToken(user.getUsername());
    }

    @Transactional
    public UserGetDto saveUser(UserPostDto dto) {
        User user = User.convertDtoToEntity(dto);
        user = userRepository.save(user);
        return User.convertEntityToDto(user);
    }

    public List<UserGetDto> getAllUsers(int page, int count) {
        Pageable pageable = PageRequest.of(page, count);
        return userRepository.findAll(pageable).stream()
                //.filter(user -> user.getType() == 1)
                .map(User::convertEntityToDto)
                .collect(Collectors.toList());
    }

    public UserGetDto getUserById(Integer id) {
        return userRepository.findById(id)
                .map(User::convertEntityToDto)
                .orElse(null);
    }

    public UserGetDto updateUser(Integer id, UserPostDto dto) {
        User user = User.convertDtoToEntity(dto);
        user.setId(id);
        user = userRepository.save(user);
        return User.convertEntityToDto(user);
    }

    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }
}