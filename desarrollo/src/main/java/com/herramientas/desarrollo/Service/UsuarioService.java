package com.herramientas.desarrollo.Service;

import org.springframework.stereotype.Service;

import com.herramientas.desarrollo.Entidades.Usuario;
import com.herramientas.desarrollo.Repositorios.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    // Crear o guardar un usuario
    public Usuario guardarUsuario(Usuario usuario) {
        return usuarioRepositorio.save(usuario);
    }

    //  Listar todos los usuarios
    public List<Usuario> listarUsuarios() {
        return usuarioRepositorio.findAll();
    }

    // Buscar usuario por ID
    public Optional<Usuario> obtenerUsuarioPorId(Long id) {
        return usuarioRepositorio.findById(id);
    }

    // Actualizar usuario
    public Usuario actualizarUsuario(Long id, Usuario datosActualizados) {
        return usuarioRepositorio.findById(id).map(usuario -> {
            // 🔹 Añade aquí más setters según los campos de tu entidad Usuario
            return usuarioRepositorio.save(usuario);
        }).orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + id));
    }

    // Eliminar usuario por ID
    public void eliminarUsuario(Long id) {
        if (usuarioRepositorio.existsById(id)) {
            usuarioRepositorio.deleteById(id);
        } else {
            throw new RuntimeException("Usuario no encontrado con ID: " + id);
        }
    }
}
