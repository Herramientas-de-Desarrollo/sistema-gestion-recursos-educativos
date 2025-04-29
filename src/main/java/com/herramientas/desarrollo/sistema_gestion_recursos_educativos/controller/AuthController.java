package com.herramientas.desarrollo.sistema_gestion_recursos_educativos.controller;

import com.herramientas.desarrollo.sistema_gestion_recursos_educativos.dto.LoginDTO;
import com.herramientas.desarrollo.sistema_gestion_recursos_educativos.dto.RegisterDTO;
import com.herramientas.desarrollo.sistema_gestion_recursos_educativos.model.Rol;
import com.herramientas.desarrollo.sistema_gestion_recursos_educativos.model.Usuario;
import com.herramientas.desarrollo.sistema_gestion_recursos_educativos.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Endpoint register
    @PostMapping("/register") // es solicitud post
    public ResponseEntity<String> registerUser(@RequestBody RegisterDTO registerDTO) {
        // Verifica si el correo ya existe
        if (usuarioRepository.findByCorreo(registerDTO.getCorreo()).isPresent()) {
            return ResponseEntity.badRequest().body("El correo ya está registrado.");
        }

        // Crea usuario
        Usuario usuario = new Usuario();
        usuario.setNombre(registerDTO.getNombre());
        usuario.setApellido(registerDTO.getApellido());
        usuario.setCorreo(registerDTO.getCorreo());
        usuario.setClave(registerDTO.getClave()); // Falta encriptacion
        usuario.setRol(Rol.valueOf(registerDTO.getRol().toUpperCase()));

        // Guarda en la base de datos
        usuarioRepository.save(usuario);

        return ResponseEntity.ok("Usuario registrado exitosamente.");
    }

    // Endpoint login
    @PostMapping("/login") // es solicitud post
    public ResponseEntity<String> loginUser(@RequestBody LoginDTO loginDTO) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findByCorreo(loginDTO.getCorreo());

        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();
            if (usuario.getClave().equals(loginDTO.getClave())) {
                return ResponseEntity.ok("Login exitoso.");
            } else {
                return ResponseEntity.status(401).body("Clave incorrecta.");
            }
        } else {
            return ResponseEntity.status(404).body("Usuario no encontrado.");
        }
    }
}

