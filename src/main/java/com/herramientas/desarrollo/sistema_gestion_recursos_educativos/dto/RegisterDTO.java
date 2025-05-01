package com.herramientas.desarrollo.sistema_gestion_recursos_educativos.dto;

import lombok.Data;
import lombok.Setter;

@Data
public class RegisterDTO {
    @Setter
    private String nombre;
    private String apellido;
    @Setter
    private String correo;
    private String clave;
    private String rol; // luego se convierte a Enum


    public String getCorreo() {
        return correo;
    }

    public String getNombre() {
        return nombre;
    }

}
