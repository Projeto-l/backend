package com.medcom.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.ignoringRequestMatchers("/h2-console/**")) // Desativa CSRF para H2
                .headers(headers -> headers.frameOptions().disable()) // Permite frames para o console do H2
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/**").permitAll()

                        .anyRequest().authenticated()) // Protege outras rotas
                .formLogin(); // Mantém login para outras páginas

        return http.build();
    }
}
