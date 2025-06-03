package com.herramientas.desarrollo.sistema_gestion_recursos_educativos.dto;

import lombok.Data;

@Data
public class PerfilUsuarioResponseDTO {
    private String biografia;
    private String intereses;
    private String area;

    private String nombreUsuario;
    private String apellidoUsuario;
}