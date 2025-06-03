package com.herramientas.desarrollo.sistema_gestion_recursos_educativos.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean

    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors() // Habilita CORS
                .and()
                .csrf().disable()
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/**").permitAll() //IMPORTANTE permite el libre acceso a las rutas
                        //.requestMatchers("/api/perfil/**").authenticated() // 🔒 Protege perfil
                        .requestMatchers("/api/perfil/**").permitAll()
                        .requestMatchers("/api/recursos/**").permitAll()// IMPORTANT permite acceso libre a las rutas
                        .anyRequest().authenticated() // Todas las demás requieren auth
                )
                .httpBasic().disable() // si no se usa  autenticación básica
                .formLogin().disable(); // IMPORTANTE si no hay login por formulario

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.setAllowedOrigins(List.of("http://localhost:4321")); // Permite solo tu frontend (Astro)
        config.setAllowedHeaders(List.of("*")); // Permite todos los headers
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS")); // Permite todos estos métodos

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config); // Aplica a todas las rutas

        return new CorsFilter(source);
    }
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


