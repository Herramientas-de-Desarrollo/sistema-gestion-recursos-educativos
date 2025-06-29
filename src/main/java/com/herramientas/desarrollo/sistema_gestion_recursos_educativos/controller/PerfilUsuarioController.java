package com.herramientas.desarrollo.sistema_gestion_recursos_educativos.controller;

import com.herramientas.desarrollo.sistema_gestion_recursos_educativos.dto.PerfilUsuarioCreateDTO;
import com.herramientas.desarrollo.sistema_gestion_recursos_educativos.dto.PerfilUsuarioResponseDTO;
import com.herramientas.desarrollo.sistema_gestion_recursos_educativos.dto.PerfilUsuarioUpdateDTO;
import com.herramientas.desarrollo.sistema_gestion_recursos_educativos.model.Usuario;
import com.herramientas.desarrollo.sistema_gestion_recursos_educativos.service.PerfilUsuarioService;
import com.herramientas.desarrollo.sistema_gestion_recursos_educativos.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/perfiles")
public class PerfilUsuarioController {

    @Autowired
    private PerfilUsuarioService perfilService;

    @Autowired
    private UsuarioService usuarioService;

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/mi-perfil")
    public ResponseEntity<Void> crearPerfil(@RequestBody PerfilUsuarioCreateDTO dto) {
        Usuario usuarioActual = usuarioService.obtenerUsuarioActual();
        perfilService.crearPerfil(usuarioActual, dto);
        return ResponseEntity.status(201).build(); // 201 Created
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/mi-perfil")
    public ResponseEntity<PerfilUsuarioResponseDTO> obtenerPerfil() {
        Usuario usuarioActual = usuarioService.obtenerUsuarioActual();
        return ResponseEntity.ok(perfilService.obtenerPerfil(usuarioActual.getId()));
    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping("/actualizar-perfil")
    public ResponseEntity<Void> actualizarPerfil(@RequestBody PerfilUsuarioUpdateDTO dto) {
        Usuario usuarioActual = usuarioService.obtenerUsuarioActual();
        perfilService.actualizarPerfil(usuarioActual, dto);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{usuarioid}")
    public ResponseEntity<PerfilUsuarioResponseDTO> obtenerPerfil(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(perfilService.obtenerPerfil(usuarioId));
    }
}