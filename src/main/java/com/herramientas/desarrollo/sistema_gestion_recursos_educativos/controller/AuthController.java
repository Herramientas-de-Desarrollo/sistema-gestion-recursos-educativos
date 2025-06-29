package com.herramientas.desarrollo.sistema_gestion_recursos_educativos.controller;

import com.herramientas.desarrollo.sistema_gestion_recursos_educativos.dto.LoginDTO;
import com.herramientas.desarrollo.sistema_gestion_recursos_educativos.dto.RegisterDTO;
import com.herramientas.desarrollo.sistema_gestion_recursos_educativos.service.UsuarioService;
import com.herramientas.desarrollo.sistema_gestion_recursos_educativos.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    // LOGIN
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO dto) {
        try {
            if (passwordEncoder.matches(dto.getClave(), usuarioService.obtenerPorCorreo(dto.getCorreo()).getClave())) {
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(dto.getCorreo(), dto.getClave())
                );

                String token = jwtUtil.generarToken(dto.getCorreo());

                return ResponseEntity.ok(Map.of("token", token));
            } else {
                return ResponseEntity.status(401).body("Clave incorrecta.");
            }
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    // REGISTRO
    @PostMapping("/registro")
    public ResponseEntity<String> registerUser(@RequestBody RegisterDTO dto) {
        try {
            usuarioService.registrarUsuario(dto);
            return ResponseEntity.ok("Usuario registrado exitosamente.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

