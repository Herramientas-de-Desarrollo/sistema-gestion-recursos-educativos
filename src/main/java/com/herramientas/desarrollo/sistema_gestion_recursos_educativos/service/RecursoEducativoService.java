package com.herramientas.desarrollo.sistema_gestion_recursos_educativos.service;

import com.herramientas.desarrollo.sistema_gestion_recursos_educativos.dto.RecursoEducativoDTO;
import com.herramientas.desarrollo.sistema_gestion_recursos_educativos.dto.RecursoEducativoRespuestaDTO;
import com.herramientas.desarrollo.sistema_gestion_recursos_educativos.model.Curso;
import com.herramientas.desarrollo.sistema_gestion_recursos_educativos.model.RecursoEducativo;
import com.herramientas.desarrollo.sistema_gestion_recursos_educativos.model.Usuario;
import com.herramientas.desarrollo.sistema_gestion_recursos_educativos.repository.CursoRepository;
import com.herramientas.desarrollo.sistema_gestion_recursos_educativos.repository.RecursoEducativoRepository;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.time.LocalDateTime;

@Service
public class RecursoEducativoService {

    @Autowired
    private RecursoEducativoRepository recursoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private UsuarioService usuarioService;

    public void crearRecurso(Usuario autor, RecursoEducativoDTO dto) throws IOException {
        RecursoEducativo recurso = new RecursoEducativo();

        recurso.setTitulo(dto.getTitulo());
        recurso.setDescripcion(dto.getDescripcion());
        recurso.setTipo(dto.getTipo());
        recurso.setFechaPublicacion(LocalDateTime.now());
        recurso.setAutor(autor);

        // Buscar el curso por nombre
        Curso curso = cursoRepository.findByNombre(dto.getCursoNombre())
                .orElseThrow(() -> new RuntimeException("Curso no encontrado con nombre: " + dto.getCursoNombre()));
        recurso.setCurso(curso);

        if ("enlace".equalsIgnoreCase(dto.getTipo())) {
            recurso.setUrl(dto.getUrl());
        } else if ("archivo".equalsIgnoreCase(dto.getTipo())) {
            // Guardar archivo
            String nombreArchivo = (dto.getArchivo().getOriginalFilename());
            Path rutaDestino = Paths.get("uploads", nombreArchivo);
            Files.createDirectories(rutaDestino.getParent());
            Files.copy(dto.getArchivo().getInputStream(), rutaDestino, StandardCopyOption.REPLACE_EXISTING);
            recurso.setNombreArchivo(nombreArchivo);
        }

        recursoRepository.save(recurso);
    }

    public List<RecursoEducativoRespuestaDTO> listarRecursos() {
        List<RecursoEducativo> recursos = recursoRepository.findAll();
        return recursos.stream().map(this::mapToDTO).toList();
    }

    public List<RecursoEducativoRespuestaDTO> listarRecursosDelDocente(Long docenteId) {
        List<RecursoEducativo> recursos = recursoRepository.findByAutorId(docenteId);
        return recursos.stream().map(this::mapToDTO).toList();
    }

    public List<RecursoEducativoRespuestaDTO> buscarPorCurso(String nombreCurso) {
        List<RecursoEducativo> recursos = recursoRepository.findByCursoNombre(nombreCurso);
        return recursos.stream().map(this::mapToDTO).toList();
    }

    public List<RecursoEducativoRespuestaDTO> buscarPorAutor(Long autorId) {
        List<RecursoEducativo> recursos = recursoRepository.findByAutorId(autorId);
        return recursos.stream().map(this::mapToDTO).toList();
    }

    public RecursoEducativoRespuestaDTO obtenerPorId(Long recursoId) {
        RecursoEducativo recurso = recursoRepository.findById(recursoId)
                .orElseThrow(() -> new RuntimeException("Recurso no encontrado"));

        return mapToDTO(recurso);
    }

    public void eliminarRecurso(Long recursoId) {
        RecursoEducativo recurso = recursoRepository.findById(recursoId)
                .orElseThrow(() -> new RuntimeException("Recurso no encontrado"));

        Usuario actual = usuarioService.obtenerUsuarioActual();

        // ADMIN puede eliminar todo
        if (actual.getRol().name().equals("ADMINISTRADOR")) {
            recursoRepository.delete(recurso);
            return;
        }

        // DOCENTE solo elimina sus propios recursos
        if (actual.getRol().name().equals("DOCENTE") &&
                recurso.getAutor().getId().equals(actual.getId())) {

            recursoRepository.delete(recurso);
            return;
        }

        throw new AccessDeniedException("No tienes permiso para eliminar este recurso");
    }

    private RecursoEducativoRespuestaDTO mapToDTO(RecursoEducativo recurso) {
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
    }
}
