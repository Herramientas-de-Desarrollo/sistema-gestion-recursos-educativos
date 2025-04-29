package com.herramientas.desarrollo.sistema_gestion_recursos_educativos.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // desactivado para pruebas tmb ps
                .authorizeHttpRequests()
                .requestMatchers("/api/auth/register", "/api/auth/login").permitAll() // Permite registrar y loguear sin autenticacion
                .anyRequest().authenticated() // todo requiere que se loguee en una sesion para autenticar
                .and()
                .formLogin().disable(); // desactiva formulario default de spring security (opcional para pruebas ps)

        return http.build();
    }
}
