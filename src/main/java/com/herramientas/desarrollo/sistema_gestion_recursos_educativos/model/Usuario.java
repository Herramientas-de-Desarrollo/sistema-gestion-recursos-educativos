package com.herramientas.desarrollo.sistema_gestion_recursos_educativos.model;

import jakarta.persistence.*;  // Anotacionaes JPA que hace que las clases de java pasen a tablas de base de datos automaticamente. Que pro.
import java.util.List;
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

    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL)
    private PerfilUsuario perfil;

    // recursos creados
    @OneToMany(mappedBy = "autor")
    private List<RecursoEducativo> recursos;
}