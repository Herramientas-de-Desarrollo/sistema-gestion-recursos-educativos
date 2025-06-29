package com.herramientas.desarrollo.sistema_gestion_recursos_educativos.service;

import com.herramientas.desarrollo.sistema_gestion_recursos_educativos.model.Usuario;
import com.herramientas.desarrollo.sistema_gestion_recursos_educativos.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByCorreo(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        return User.builder()
                .username(usuario.getCorreo())
                .password(usuario.getClave())
                .roles(usuario.getRol().name()) // Asume que Rol es un enum
                .build();
    }
}
