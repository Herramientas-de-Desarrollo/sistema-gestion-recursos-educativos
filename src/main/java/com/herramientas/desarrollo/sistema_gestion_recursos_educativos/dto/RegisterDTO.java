package com.herramientas.desarrollo.sistema_gestion_recursos_educativos.dto;

import com.herramientas.desarrollo.sistema_gestion_recursos_educativos.model.Rol;
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

<<<<<<< HEAD

    public String getCorreo() {
        return correo;
    }

    public String getNombre() {
        return nombre;
    }

=======
>>>>>>> 104443c1c7d8a08ffab02c87aba826ac0158e7a3
    public Rol getRolEnum() {
        return Rol.valueOf(this.rol.toUpperCase());
    }
}


