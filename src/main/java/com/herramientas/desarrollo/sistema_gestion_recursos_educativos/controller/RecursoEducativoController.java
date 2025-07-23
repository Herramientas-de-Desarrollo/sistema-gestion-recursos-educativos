package com.herramientas.desarrollo.sistema_gestion_recursos_educativos.controller;

import com.herramientas.desarrollo.sistema_gestion_recursos_educativos.dto.RecursoEducativoDTO;
import com.herramientas.desarrollo.sistema_gestion_recursos_educativos.dto.RecursoEducativoRespuestaDTO;
import com.herramientas.desarrollo.sistema_gestion_recursos_educativos.model.Usuario;
import com.herramientas.desarrollo.sistema_gestion_recursos_educativos.service.RecursoEducativoService;
import com.herramientas.desarrollo.sistema_gestion_recursos_educativos.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/recursos")
public class RecursoEducativoController {

    @Autowired
    private RecursoEducativoService recursoService;

    @Autowired
    private UsuarioService usuarioService;

    @PreAuthorize("hasAnyRole('ADMINISTRADOR', 'DOCENTE')")
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> crearRecurso(@ModelAttribute RecursoEducativoDTO dto) throws IOException {
        Usuario usuarioActual = usuarioService.obtenerUsuarioActual();
        recursoService.crearRecurso(usuarioActual, dto);
        return ResponseEntity.status(201).build(); // 201 Created
    }

    @GetMapping
    public List<RecursoEducativoRespuestaDTO> listar() {
        return recursoService.listarRecursos();
    }

    @GetMapping("/curso/{nombre}")
    public List<RecursoEducativoRespuestaDTO> buscarPorCurso(@PathVariable String nombre) {
        return recursoService.buscarPorCurso(nombre);
    }

    @GetMapping("/autor/{id}")
    public List<RecursoEducativoRespuestaDTO> buscarPorAutor(@PathVariable Long id) {
        return recursoService.buscarPorAutor(id);
    }

    @PreAuthorize("hasRole('DOCENTE')")
    @GetMapping("/mis-recursos")
    public List<RecursoEducativoRespuestaDTO> listarRecursosDelDocente() {
        Usuario docenteActual = usuarioService.obtenerUsuarioActual();
        return recursoService.listarRecursosDelDocente(docenteActual.getId());
    }


    @PreAuthorize("hasAnyRole('ADMINISTRADOR', 'DOCENTE')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        recursoService.eliminarRecurso(id);
        return ResponseEntity.noContent().build(); // 204 No Content
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecursoEducativoRespuestaDTO> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(recursoService.obtenerPorId(id));
    }
}
