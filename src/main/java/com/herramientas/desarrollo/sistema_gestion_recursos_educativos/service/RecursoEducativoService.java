package com.herramientas.desarrollo.sistema_gestion_recursos_educativos.service;

import com.herramientas.desarrollo.sistema_gestion_recursos_educativos.model.RecursoEducativo;
import com.herramientas.desarrollo.sistema_gestion_recursos_educativos.repository.RecursoEducativoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecursoEducativoService {

    @Autowired
    private RecursoEducativoRepository recursoRepository;

    public RecursoEducativo crearRecurso(RecursoEducativo recurso) {
        return recursoRepository.save(recurso);
    }

    public List<RecursoEducativo> listarRecursos() {
        return recursoRepository.findAll();
    }

    public void eliminarRecurso(Long id) {
        recursoRepository.deleteById(id);
    }

    public RecursoEducativo obtenerPorId(Long id) {
        return recursoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Recurso no encontrado"));
    }
}
