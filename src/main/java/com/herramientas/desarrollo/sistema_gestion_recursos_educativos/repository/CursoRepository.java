package com.herramientas.desarrollo.sistema_gestion_recursos_educativos.repository;

import com.herramientas.desarrollo.sistema_gestion_recursos_educativos.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CursoRepository extends JpaRepository<Curso, Long> {
    Optional<Curso> findByNombre(String nombre);
}
