package com.herramientas.desarrollo.sistema_gestion_recursos_educativos.service;

import com.herramientas.desarrollo.sistema_gestion_recursos_educativos.dto.PerfilUsuarioCreateDTO;
import com.herramientas.desarrollo.sistema_gestion_recursos_educativos.dto.PerfilUsuarioResponseDTO;
import com.herramientas.desarrollo.sistema_gestion_recursos_educativos.dto.PerfilUsuarioUpdateDTO;
import com.herramientas.desarrollo.sistema_gestion_recursos_educativos.model.PerfilUsuario;
import com.herramientas.desarrollo.sistema_gestion_recursos_educativos.model.Usuario;
import com.herramientas.desarrollo.sistema_gestion_recursos_educativos.repository.PerfilUsuarioRepository;
import com.herramientas.desarrollo.sistema_gestion_recursos_educativos.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PerfilUsuarioService {

    @Autowired
    private PerfilUsuarioRepository perfilRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public void crearPerfil(Usuario usuario , PerfilUsuarioCreateDTO dto) {
        // Verifica si ya tiene un perfil
        if (perfilRepository.findByUsuarioCorreo(usuario.getCorreo()).isPresent()) {
            throw new RuntimeException("Este usuario ya tiene un perfil.");
        }

        PerfilUsuario perfil = new PerfilUsuario();
        perfil.setUsuario(usuario);
        perfil.setBiografia(dto.getBiografia());
        perfil.setIntereses(dto.getIntereses());
        perfil.setArea(dto.getArea());

        perfilRepository.save(perfil);
    }

    public PerfilUsuario actualizarPerfil(Usuario usuario, PerfilUsuarioUpdateDTO dto) {
        PerfilUsuario perfil = perfilRepository.findByUsuarioCorreo(usuario.getCorreo())
                .orElse(new PerfilUsuario());

        perfil.setUsuario(usuario);
        perfil.setBiografia(dto.getBiografia());
        perfil.setIntereses(dto.getIntereses());
        perfil.setArea(dto.getArea());

        return perfilRepository.save(perfil);
    }

    public PerfilUsuarioResponseDTO obtenerPerfil(Long usuarioid) {
        Usuario usuario = usuarioRepository.findById(usuarioid)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        PerfilUsuario perfil = perfilRepository.findById(usuario.getPerfil().getId())
                .orElseThrow(() -> new RuntimeException("Perfil no encontrado"));

        return mapToDTO(perfil);
    }

    private PerfilUsuarioResponseDTO mapToDTO(PerfilUsuario perfil) {
        PerfilUsuarioResponseDTO dto = new PerfilUsuarioResponseDTO();
        dto.setBiografia(perfil.getBiografia());
        dto.setIntereses(perfil.getIntereses());
        dto.setArea(perfil.getArea());
        dto.setNombreUsuario(perfil.getUsuario().getNombre());
        dto.setApellidoUsuario(perfil.getUsuario().getApellido());
        return dto;
    }
}