package com.enlatados.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Desactiva CSRF para pruebas con Postman
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll() // Permitir todos los endpoints (por ahora)
                )
                .httpBasic(Customizer.withDefaults()); // Permite login básico para pruebas si lo necesitas

        return http.build();
    }
}