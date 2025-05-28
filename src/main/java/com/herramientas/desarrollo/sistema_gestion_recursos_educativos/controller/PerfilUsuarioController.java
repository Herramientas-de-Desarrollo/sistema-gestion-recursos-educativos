package com.herramientas.desarrollo.sistema_gestion_recursos_educativos.controller;

import com.herramientas.desarrollo.sistema_gestion_recursos_educativos.dto.PerfilUsuarioCreateDTO;
import com.herramientas.desarrollo.sistema_gestion_recursos_educativos.dto.PerfilUsuarioResponseDTO;
import com.herramientas.desarrollo.sistema_gestion_recursos_educativos.dto.PerfilUsuarioUpdateDTO;
import com.herramientas.desarrollo.sistema_gestion_recursos_educativos.service.PerfilUsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/perfil")

public class PerfilUsuarioController {

    private final PerfilUsuarioService perfilUsuarioService;

    public PerfilUsuarioController(PerfilUsuarioService perfilUsuarioService) {
        this.perfilUsuarioService = perfilUsuarioService;
    }

    @GetMapping
    public ResponseEntity<PerfilUsuarioResponseDTO> obtenerPerfil(Authentication auth) {
        String correo = auth.getName();
        return ResponseEntity.ok(perfilUsuarioService.obtenerPerfil(correo));
    }

    @PutMapping
    public ResponseEntity<Void> actualizarPerfil(@RequestBody PerfilUsuarioUpdateDTO dto, Authentication auth) {
        String correo = auth.getName();
        perfilUsuarioService.actualizarPerfil(correo, dto);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{usuarioId}")
    public ResponseEntity<Void> crearPerfil(@PathVariable Long usuarioId, @RequestBody PerfilUsuarioCreateDTO dto) {
        perfilUsuarioService.crearPerfil(usuarioId, dto);
        return ResponseEntity.status(201).build(); // 201 Created
    }
}
