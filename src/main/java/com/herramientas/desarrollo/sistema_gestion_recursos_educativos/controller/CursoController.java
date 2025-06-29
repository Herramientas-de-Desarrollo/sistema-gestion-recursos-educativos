package com.herramientas.desarrollo.sistema_gestion_recursos_educativos.controller;

import com.herramientas.desarrollo.sistema_gestion_recursos_educativos.dto.CursoCreateDTO;
import com.herramientas.desarrollo.sistema_gestion_recursos_educativos.dto.CursoResponseDTO;
import com.herramientas.desarrollo.sistema_gestion_recursos_educativos.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cursos")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @PreAuthorize("hasAnyRole('ADMINISTRADOR')")
    @PostMapping
    public ResponseEntity<Void> crearCurso(@RequestBody CursoCreateDTO dto) {
        cursoService.crearCurso(dto);
        return ResponseEntity.status(201).build(); //
    }

    @GetMapping
    public List<CursoResponseDTO> listar() {
        return cursoService.listarCursos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CursoResponseDTO> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(cursoService.obtenerPorId(id));
    }
}
