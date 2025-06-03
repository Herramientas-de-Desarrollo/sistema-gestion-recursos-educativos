package com.herramientas.desarrollo.sistema_gestion_recursos_educativos.controller;

import com.herramientas.desarrollo.sistema_gestion_recursos_educativos.dto.LoginDTO;
import com.herramientas.desarrollo.sistema_gestion_recursos_educativos.dto.RegisterDTO;
import com.herramientas.desarrollo.sistema_gestion_recursos_educativos.model.Usuario;
<<<<<<< HEAD
import com.herramientas.desarrollo.sistema_gestion_recursos_educativos.repository.UsuarioRepository;
=======
>>>>>>> 104443c1c7d8a08ffab02c87aba826ac0158e7a3
import com.herramientas.desarrollo.sistema_gestion_recursos_educativos.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
<<<<<<< HEAD
    private UsuarioService usuarioService;

    @Autowired
=======
>>>>>>> 104443c1c7d8a08ffab02c87aba826ac0158e7a3
    private PasswordEncoder passwordEncoder;

    // Registro de usuario
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody RegisterDTO dto) {
        try {
            Usuario usuario = new Usuario();
            usuario.setNombre(dto.getNombre());
            usuario.setApellido(dto.getApellido());
            usuario.setCorreo(dto.getCorreo());
            usuario.setClave(dto.getClave()); // Se encripta en el servicio
            usuario.setRol(dto.getRolEnum()); // Método personalizado del DTO

            usuarioService.registrarUsuario(usuario);
            return ResponseEntity.ok("Usuario registrado exitosamente.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Login de usuario
    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody LoginDTO dto) {
<<<<<<< HEAD

        try {
            Usuario usuario = usuarioService.obtenerPorCorreo(dto.getCorreo());


=======
        try {
            Usuario usuario = usuarioService.obtenerPorCorreo(dto.getCorreo());

>>>>>>> 104443c1c7d8a08ffab02c87aba826ac0158e7a3
            if (passwordEncoder.matches(dto.getClave(), usuario.getClave())) {
                return ResponseEntity.ok("Login exitoso.");
            } else {
                return ResponseEntity.status(401).body("Clave incorrecta.");
            }
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    // Buscar usuario por correo (GET) (esto luego se cambiara a una clase especifica para estos casos)
    @GetMapping("/buscar")
    public ResponseEntity<Usuario> buscarPorCorreo(@RequestParam String correo) {
        return usuarioRepository.findByCorreo(correo)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
