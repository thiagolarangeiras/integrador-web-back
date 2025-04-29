package com.github.integrador.auth;

import com.github.integrador.Usuario.Cargo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Collections;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    @Autowired private UserAuthenticationFilter userAuthenticationFilter;

    public static final String[] AUTH_REQUIRED = {
            "/teste/login",
            "/cliente/**",
            "/fornecedor/**",
            "/marca/**",
            "/pedido/**",
            "/produto/**",
            "/vendedor/**",
    };

    public static final String[] AUTH_CARGO_ADM = {
            "/teste/adm",
            "/usuario/**",
    };

    public static final String[] AUTH_CARGO_VENDEDOR = {
            "/teste/vendedor"
    };

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();

        //configuration.setAllowedOrigins(List.of("https://seusite.com", "https://outrosite.com"));
        config.setAllowedOrigins(Collections.singletonList("*"));

        //config.setAllowedHeaders(List.of("*")); // ou especifique ("Authorization", "Content-Type", etc)
        //config.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type", "X-Requested-With"));
        config.setAllowedHeaders(Collections.singletonList("*"));

        //config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowedMethods(Collections.singletonList("*"));
        //config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .cors(Customizer.withDefaults())
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(userAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(AUTH_REQUIRED).authenticated()
                        .requestMatchers(AUTH_CARGO_ADM).hasAuthority(Cargo.adm.name())
                        .requestMatchers(AUTH_CARGO_VENDEDOR).hasAuthority(Cargo.vendedor.name())
                        //.requestMatchers(AUTH_CARGO_VENDEDOR).hasRole(Cargo.vendedor.name())  // Role nao funciona nesse caso
                        .anyRequest().permitAll()
                )
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