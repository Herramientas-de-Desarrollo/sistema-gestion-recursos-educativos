package com.herramientas.desarrollo.sistema_gestion_recursos_educativos.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "perfiles_usuarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class PerfilUsuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String biografia;
    private String intereses;
    private String area;

    @OneToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private Usuario usuario;
}
