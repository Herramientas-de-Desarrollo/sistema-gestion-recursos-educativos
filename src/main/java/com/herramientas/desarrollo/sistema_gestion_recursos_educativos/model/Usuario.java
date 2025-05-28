package com.herramientas.desarrollo.sistema_gestion_recursos_educativos.model;

import jakarta.persistence.*;  // Anotacionaes JPA que hace que las clases de java pasen a tablas de base de datos automaticamente. Que pro.
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity // Anotacion que esta clase es una entidad que se almacenara en la base de datos
@Table(name = "usuarios") // Especifica la tabla donde se almacenara
@Data // Lombok genera getters, setters, constructores, toString automaticamente. Que pro.


@Getter
@Setter

public class Usuario {

    @Id // lo marca como llave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // PK, permite que sea icrementable
    private Long id;


    //@Column(unique = true, nullable = false) // unique, no se puede repetir; nullable, no puede estar vacio
    private String nombre;
    private String apellido;

    @Column(unique = true, nullable = false) // unique, no se puede repetir; nullable, no puede estar vacio
    private String correo;

    @Column(nullable = false)
    private String clave;

    @Enumerated(EnumType.STRING) // lo guarda como cadena de texto en la base de datos
    @Column(nullable = false)
    private Rol rol;
}

    /*
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

*/

