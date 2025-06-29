package com.herramientas.desarrollo.sistema_gestion_recursos_educativos.service;

import com.herramientas.desarrollo.sistema_gestion_recursos_educativos.dto.RegisterDTO;
import com.herramientas.desarrollo.sistema_gestion_recursos_educativos.model.Usuario;
import com.herramientas.desarrollo.sistema_gestion_recursos_educativos.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void registrarUsuario(RegisterDTO dto) {
       Optional<Usuario> existente = usuarioRepository.findByCorreo(dto.getCorreo());

        if (existente.isPresent()) {
            throw new IllegalArgumentException("Ya existe un usuario con ese correo.");
        }

        Usuario usuario = new Usuario();

        usuario.setNombre(dto.getNombre());
        usuario.setApellido(dto.getApellido());
        usuario.setCorreo(dto.getCorreo());
        usuario.setClave(passwordEncoder.encode(dto.getClave()));
        usuario.setRol(dto.getRolEnum()); // Dto


        usuarioRepository.save(usuario);
    }

    public Usuario obtenerPorCorreo(String correo) {
        return usuarioRepository.findByCorreo(correo)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con correo: " + correo));
    }

    public Optional<Usuario> obtenerPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    public void eliminarUsuarioPorId(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new RuntimeException("Usuario no encontrado con id: " + id);
        }
        usuarioRepository.deleteById(id);
    }

    public List<Usuario> buscarPorNombreOApellido(String nombre) {
        return usuarioRepository.findByNombreContainingIgnoreCaseOrApellidoContainingIgnoreCase(nombre, nombre);
    }

    public Usuario obtenerUsuarioActual() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return usuarioRepository.findByCorreo(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
    }
}
