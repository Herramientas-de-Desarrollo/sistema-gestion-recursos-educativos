package com.herramientas.desarrollo.sistema_gestion_recursos_educativos.service;

import com.herramientas.desarrollo.sistema_gestion_recursos_educativos.dto.PerfilUsuarioCreateDTO;
import com.herramientas.desarrollo.sistema_gestion_recursos_educativos.dto.PerfilUsuarioResponseDTO;
import com.herramientas.desarrollo.sistema_gestion_recursos_educativos.dto.PerfilUsuarioUpdateDTO;
import com.herramientas.desarrollo.sistema_gestion_recursos_educativos.model.PerfilUsuario;
import com.herramientas.desarrollo.sistema_gestion_recursos_educativos.model.Usuario;
import com.herramientas.desarrollo.sistema_gestion_recursos_educativos.repository.PerfilUsuarioRepository;
import com.herramientas.desarrollo.sistema_gestion_recursos_educativos.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class PerfilUsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PerfilUsuarioRepository perfilUsuarioRepository;

    public PerfilUsuarioService(UsuarioRepository usuarioRepository, PerfilUsuarioRepository perfilUsuarioRepository) {
        this.usuarioRepository = usuarioRepository;
        this.perfilUsuarioRepository = perfilUsuarioRepository;
    }

    public PerfilUsuarioResponseDTO obtenerPerfil(String correo) {
        PerfilUsuario perfil = perfilUsuarioRepository.findByUsuarioCorreo(correo)
                .orElseThrow(() -> new RuntimeException("Perfil no encontrado"));

        return mapToDTO(perfil);
    }


    public void crearPerfil(Long usuarioId, PerfilUsuarioCreateDTO dto) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + usuarioId));

        // Verifica si ya tiene un perfil
        if (perfilUsuarioRepository.findByUsuarioCorreo(usuario.getCorreo()).isPresent()) {
            throw new RuntimeException("Este usuario ya tiene un perfil.");
        }

        PerfilUsuario perfil = new PerfilUsuario();
        perfil.setUsuario(usuario);
        perfil.setBiografia(dto.getBiografia());
        perfil.setIntereses(dto.getIntereses());
        perfil.setArea(dto.getArea());

        perfilUsuarioRepository.save(perfil);
    }


    public void actualizarPerfil(String correo, PerfilUsuarioUpdateDTO dto) {
        Usuario usuario = usuarioRepository.findByCorreo(correo)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        PerfilUsuario perfil = perfilUsuarioRepository.findByUsuarioCorreo(correo)
                .orElse(new PerfilUsuario());

        perfil.setUsuario(usuario);
        perfil.setBiografia(dto.getBiografia());
        perfil.setIntereses(dto.getIntereses());
        perfil.setArea(dto.getArea());

        perfilUsuarioRepository.save(perfil);
    }

    private PerfilUsuarioResponseDTO mapToDTO(PerfilUsuario perfil) {
        PerfilUsuarioResponseDTO dto = new PerfilUsuarioResponseDTO();
        dto.setBiografia(perfil.getBiografia());
        dto.setIntereses(perfil.getIntereses());
        dto.setArea(perfil.getArea());
        return dto;
    }
}
