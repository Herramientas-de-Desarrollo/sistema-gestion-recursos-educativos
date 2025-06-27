package com.herramientas.desarrollo.sistema_gestion_recursos_educativos.repository;

import com.herramientas.desarrollo.sistema_gestion_recursos_educativos.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long> {
}
