package com.herramientas.desarrollo.sistema_gestion_recursos_educativos.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "detalle_recursos")
@Data
public class DetalleRecurso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descripcion;
    private String url;  // Enlace externo

    // Relación con archivos
    @OneToMany(mappedBy = "detalleRecurso")
    private List<Archivo> archivos;

    @OneToOne
    @JoinColumn(name = "recurso_id", referencedColumnName = "id")
    private RecursoEducativo recursoEducativo;
}
