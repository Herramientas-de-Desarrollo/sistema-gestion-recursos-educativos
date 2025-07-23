package com.herramientas.desarrollo.sistema_gestion_recursos_educativos.security;

import org.springframework.stereotype.Component;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;


@Component
public class JwtUtil {

    private final String SECRET_KEY = "clave-muy-secreta-y-segura-para-el-jwt-que-tenga-al-menos-256-bits";

    private final long EXPIRACION = 1000 * 60 * 60 * 5; // 5 horas

    private Key getKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    // Generar token JWT
    public String generarToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRACION))
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // Obtener email del token
    public String extraerEmail(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    // Validar token
    public boolean validarToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(getKey())
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }
}
