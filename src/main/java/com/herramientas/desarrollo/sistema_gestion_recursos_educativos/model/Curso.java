package com.herramientas.desarrollo.sistema_gestion_recursos_educativos.model;

import jakarta.persistence.*;
import java.util.List;
import lombok.Data;

@Entity
@Table(name = "cursos")
@Data
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    // Relación con recursos
    @OneToMany(mappedBy = "curso")
    private List<RecursoEducativo> recursos;
}

