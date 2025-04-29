package com.herramientas.desarrollo.sistema_gestion_recursos_educativos.repository;

import com.herramientas.desarrollo.sistema_gestion_recursos_educativos.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository // es repositorio de Spring, se comunica con la base de datos
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Buscar usuario por correo
    Optional<Usuario> findByCorreo(String correo);
}