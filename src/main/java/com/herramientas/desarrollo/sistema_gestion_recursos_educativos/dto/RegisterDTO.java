package com.herramientas.desarrollo.sistema_gestion_recursos_educativos.dto;

import lombok.Data;

@Data
public class RegisterDTO {
    private String nombre;
    private String apellido;
    private String correo;
    private String clave;
    private String rol; // luego se convierte a Enum
}
