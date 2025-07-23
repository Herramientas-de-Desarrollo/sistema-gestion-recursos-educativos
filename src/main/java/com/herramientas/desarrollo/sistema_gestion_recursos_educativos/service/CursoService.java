package com.herramientas.desarrollo.sistema_gestion_recursos_educativos.service;

import com.herramientas.desarrollo.sistema_gestion_recursos_educativos.dto.CursoCreateDTO;
import com.herramientas.desarrollo.sistema_gestion_recursos_educativos.dto.CursoResponseDTO;
import com.herramientas.desarrollo.sistema_gestion_recursos_educativos.model.Curso;
import com.herramientas.desarrollo.sistema_gestion_recursos_educativos.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    public void crearCurso(CursoCreateDTO dto) {
        Curso curso = new Curso();
        curso.setNombre(dto.getNombre());

        cursoRepository.save(curso);
    }

    public List<CursoResponseDTO> listarCursos() {
        List<Curso> cursos = cursoRepository.findAll();
        return cursos.stream().map(this::mapToDTO).toList();
    }

    public CursoResponseDTO obtenerPorId(Long id) {
        Curso curso = cursoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Curso no encontrado"));

        return mapToDTO(curso);
    }

    public CursoResponseDTO mapToDTO(Curso curso) {
        CursoResponseDTO dto = new CursoResponseDTO();
        dto.setId(curso.getId());
        dto.setNombre(curso.getNombre());

        return dto;
    }
}
