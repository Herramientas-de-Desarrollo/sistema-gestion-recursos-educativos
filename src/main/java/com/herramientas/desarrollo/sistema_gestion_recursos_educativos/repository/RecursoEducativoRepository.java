package com.herramientas.desarrollo.sistema_gestion_recursos_educativos.repository;

import com.herramientas.desarrollo.sistema_gestion_recursos_educativos.model.RecursoEducativo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecursoEducativoRepository extends JpaRepository<RecursoEducativo, Long>{
    List<RecursoEducativo> findByCursoNombre(String nombreCurso);
    List<RecursoEducativo> findByAutorId(Long autorId);
}
