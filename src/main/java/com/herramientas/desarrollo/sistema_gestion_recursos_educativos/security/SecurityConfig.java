package com.herramientas.desarrollo.sistema_gestion_recursos_educativos.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean

    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/**").permitAll() //IMPORTANTE permite el libre acceso a las rutas
                        .anyRequest().authenticated()
                )
                .httpBasic().disable() // si no se usa  autenticación básica
                .formLogin().disable(); // IMPORTANTE si no hay login por formulario

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /*public SecurityFilterChain filterChain(HttpSecurity http) throws Exception { 3RO fallo
        http
                .csrf().disable() // Desactiva CSRF para Postman
                .authorizeHttpRequests()
                .requestMatchers("/api/auth/**").permitAll() // Permite acceso libre a auth
                .anyRequest().authenticated() // El resto requiere autenticación
                .and()
                .httpBasic(); // Habilita autenticación básica si fuera necesario
        return http.build();
    }*/


        /*public SecurityFilterChain filterChain(HttpSecurity http) throws Exception { 1ERO fallo
        http
                .csrf().disable() // desactivado para pruebas tmb ps
                .authorizeHttpRequests()
                .requestMatchers("/api/auth/register", "/api/auth/login").permitAll() // Permite registrar y loguear sin autenticacion
                .anyRequest().authenticated() // todo requiere que se loguee en una sesion para autenticar
                .and()
                .formLogin().disable(); // desactiva formulario default de spring security (opcional para pruebas ps)

        return http.build();
    }*/

    /*public SecurityFilterChain filterChain(HttpSecurity http) throws Exception { 2DO fallo
        http
                .csrf().disable() // Desactiva CSRF para pruebas con Postman
                .authorizeHttpRequests()
                .requestMatchers("/api/auth/**").permitAll() // Permite libre acceso a /api/auth
                .anyRequest().authenticated(); // El resto sí requiere auth
        return http.build();
    }*/

}
