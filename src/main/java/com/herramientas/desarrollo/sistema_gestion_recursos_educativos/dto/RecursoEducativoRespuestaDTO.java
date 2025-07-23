package com.herramientas.desarrollo.sistema_gestion_recursos_educativos.dto;

import lombok.Data;

@Data
public class RecursoEducativoRespuestaDTO {
    private Long id;
    private String titulo;
    private String descripcion;
    private String tipo;
    private String url;
    private String nombreArchivo;
    private String cursoNombre;

    private Long autorId;
    private String autorNombre;
}
