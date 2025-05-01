package com.herramientas.desarrollo.sistema_gestion_recursos_educativos.repository;

import com.herramientas.desarrollo.sistema_gestion_recursos_educativos.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository //El repositorio de Spring, el cual se comunica con la BD
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Buscamos el usuario por correo
    Optional<Usuario> findByCorreo(String correo);
}