package com.herramientas.desarrollo.sistema_gestion_recursos_educativos.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "archivos")
@Data
public class Archivo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombreArchivo;

    @ManyToOne
    @JoinColumn(name = "detalleRecurso_id")
    private DetalleRecurso detalleRecurso;
}
