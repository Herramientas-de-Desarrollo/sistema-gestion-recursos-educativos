package com.herramientas.desarrollo.sistema_gestion_recursos_educativos.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "recursos_educativos")
@Data
public class RecursoEducativo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String descripcion;
    private String tipo; // "archivo" o "enlace"
    private String url;  // Enlace externo
    private String nombreArchivo; // Nombre del archivo subido (si aplica)

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario autor;
}
