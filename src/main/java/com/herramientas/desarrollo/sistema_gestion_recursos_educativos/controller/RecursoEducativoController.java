package com.herramientas.desarrollo.sistema_gestion_recursos_educativos.controller;


import com.herramientas.desarrollo.sistema_gestion_recursos_educativos.dto.RecursoEducativoDTO;
import com.herramientas.desarrollo.sistema_gestion_recursos_educativos.dto.RecursoEducativoRespuestaDTO;
import com.herramientas.desarrollo.sistema_gestion_recursos_educativos.model.RecursoEducativo;
import com.herramientas.desarrollo.sistema_gestion_recursos_educativos.model.Usuario;
import com.herramientas.desarrollo.sistema_gestion_recursos_educativos.repository.UsuarioRepository;
import com.herramientas.desarrollo.sistema_gestion_recursos_educativos.service.RecursoEducativoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/recursos")
public class RecursoEducativoController {

    @Autowired
    private RecursoEducativoService recursoService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Crear recurso tipo ENLACE o ARCHIVO
    @PostMapping
    public ResponseEntity<?> crearRecurso(@RequestBody RecursoEducativo recurso) {
        if (recurso.getAutor() == null || recurso.getAutor().getId() == null) {
            return ResponseEntity.badRequest().body("El ID del autor es requerido");
        }

        Long autorId = recurso.getAutor().getId();
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(autorId);
        if (usuarioOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Usuario con ID " + autorId + " no encontrado");
        }

        recurso.setAutor(usuarioOpt.get());
        RecursoEducativo guardado = recursoService.crearRecurso(recurso);

        // Crear una copia con datos sensibles ocultos
        guardado.getAutor().setClave(null); // Oculta la contraseña

        return ResponseEntity.ok(guardado);
    }

    // Subir archivo (Multipart)
    @PostMapping("/subir")
    public ResponseEntity<String> subirArchivo(@RequestParam("archivo") MultipartFile archivo) {
        try {
            String nombreArchivo = archivo.getOriginalFilename();
            Path rutaDestino = Paths.get("uploads", nombreArchivo);
            Files.createDirectories(rutaDestino.getParent());
            Files.copy(archivo.getInputStream(), rutaDestino, StandardCopyOption.REPLACE_EXISTING);

            return ResponseEntity.ok("Archivo subido correctamente: " + nombreArchivo);
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Error al subir archivo: " + e.getMessage());
        }
    }

    // Obtener todos los recursos
    @GetMapping
    public List<RecursoEducativoRespuestaDTO> listarRecursos() {
        List<RecursoEducativo> recursos = recursoService.listarRecursos();
        return recursos.stream().map(recurso -> {
            RecursoEducativoRespuestaDTO dto = new RecursoEducativoRespuestaDTO();
            dto.setId(recurso.getId());
            dto.setTitulo(recurso.getTitulo());
            dto.setDescripcion(recurso.getDescripcion());
            dto.setTipo(recurso.getTipo());
            dto.setUrl(recurso.getUrl());
            dto.setNombreArchivo(recurso.getNombreArchivo());

            if (recurso.getAutor() != null) {
                dto.setAutorId(recurso.getAutor().getId());
                dto.setAutorNombre(recurso.getAutor().getNombre());
            }

            return dto;
        }).toList();
    }

    // Obtener recurso por ID
    @GetMapping("/{id}")
    public ResponseEntity<RecursoEducativoRespuestaDTO> obtenerPorId(@PathVariable Long id) {
        RecursoEducativo recurso = recursoService.obtenerPorId(id);
        if (recurso == null) {
            return ResponseEntity.notFound().build();
        }

        RecursoEducativoRespuestaDTO dto = new RecursoEducativoRespuestaDTO();
        dto.setId(recurso.getId());
        dto.setTitulo(recurso.getTitulo());
        dto.setDescripcion(recurso.getDescripcion());
        dto.setTipo(recurso.getTipo());
        dto.setUrl(recurso.getUrl());
        dto.setNombreArchivo(recurso.getNombreArchivo());

        if (recurso.getAutor() != null) {
            dto.setAutorId(recurso.getAutor().getId());
            dto.setAutorNombre(recurso.getAutor().getNombre());
        }

        return ResponseEntity.ok(dto);
    }

    // Eliminar recurso
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        recursoService.eliminarRecurso(id);
        return ResponseEntity.noContent().build(); // 204 No Content
    }


//////////////////////////////////////////////////////////////////////////////////////////




}
