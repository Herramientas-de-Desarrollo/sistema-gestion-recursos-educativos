package com.herramientas.desarrollo.sistema_gestion_recursos_educativos.dto;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class DetalleRecursoDTO {
    private String titulo;
    private String descripcion;
    private String tipo; // "archivo" o "enlace"
    private String url;  // Si es enlace externo
    private String nombreArchivo;
    private String cursoNombre;

    // Solo si es tipo "archivo"
    private MultipartFile archivo;
}
