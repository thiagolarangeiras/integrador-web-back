package com.github.integrador.Auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    @Autowired private UserAuthenticationFilter userAuthenticationFilter;

    public static final String[] AUTH_NOT_REQUIRED = {
            "/auth",
            "/teste",
            "/swagger-ui/index.html",
    };

    public static final String[] AUTH_REQUIRED = {
            "/teste-login",
            "/Cliente*",
            "/Fornecedor*",
            "/Marca*",
            "/Pedido*",
            "/Produto*",
            "/Usuario*",
            "/Vendedor*"
    };

    public static final String[] AUTH_CARGO_ADM = {
            ""
    };

    public static final String[] AUTH_CARGO_VENDEDOR = {
            ""
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().authorizeHttpRequests()
                .requestMatchers(AUTH_REQUIRED).authenticated()
                .requestMatchers(AUTH_CARGO_ADM).hasRole("ADM")
                .requestMatchers(AUTH_CARGO_VENDEDOR).hasRole("VENDEDOR")
                .requestMatchers(AUTH_NOT_REQUIRED).permitAll()
                .anyRequest().permitAll()
                .and().addFilterBefore(userAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}


// @Configuration
// @EnableWebSecurity
// public class SecurityConfiguration {

//     public static final String [] ENDPOINTS_WITH_AUTHENTICATION_NOT_REQUIRED = {
//             "/users/login", //url que usaremos para fazer login
//             "/users" //url que usaremos para criar um usuário
//     };

// }
