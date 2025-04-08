package com.github.integrador.Auth;

import com.github.integrador.Usuario.Usuario;
import com.github.integrador.Usuario.UsuarioRepo;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;

@Component
public class UserAuthenticationFilter extends OncePerRequestFilter {
    @Autowired private JwtTokenService jwtTokenService;
    @Autowired private UsuarioRepo userRepository;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        if(checkIfEndpointIsPrivate(request)){
            String token = request.getHeader("Authorization");
            if (token == null) {
                throw new RuntimeException("O token está ausente.");
            }
            token = token.replace("Bearer ", "");

            String subject = jwtTokenService.getSubjectFromToken(token);
            Usuario user = userRepository.findByUsername(subject).get();
            UserDetailsImpl userDetails = new UserDetailsImpl(user);
            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    userDetails.getUsername(),
                    null,
                    userDetails.getAuthorities()
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }

    private boolean checkIfEndpointIsPrivate(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        return Arrays.asList(SecurityConfiguration.AUTH_REQUIRED).contains(requestURI);
    }
}