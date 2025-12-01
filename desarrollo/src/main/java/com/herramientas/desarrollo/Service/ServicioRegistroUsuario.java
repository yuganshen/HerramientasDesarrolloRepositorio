package com.herramientas.desarrollo.Service;

import com.herramientas.desarrollo.DTOs.RegistroUsuarioDto;
import com.herramientas.desarrollo.Entidades.Usuario;
import com.herramientas.desarrollo.Repositorios.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Servicio de Registro de Usuarios
 * 
 * Gestiona el proceso de registro de nuevos usuarios:
 * 1. Valida que los datos sean correctos
 * 2. Verifica que el email no esté registrado
 * 3. Cifra la contraseña con BCrypt
 * 4. Guarda el usuario en la base de datos
 * 5. Retorna el usuario creado o un mensaje de error
 * 
 * Seguridad:
 * - Las contraseñas se cifran SIEMPRE con BCrypt
 * - Nunca se almacenan contraseñas en texto plano
 * - Se valida que el email no esté duplicado
 */
@Service
public class ServicioRegistroUsuario {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Registra un nuevo usuario en el sistema
     * 
     * Proceso:
     * 1. Verifica que el email no esté ya registrado
     * 2. Crea un nuevo usuario con los datos proporcionados
     * 3. Cifra la contraseña usando BCrypt
     * 4. Asigna el rol de CLIENTE por defecto
     * 5. Establece la fecha de registro actual
     * 6. Establece el estado como activo
     * 7. Guarda el usuario en la base de datos
     * 
     * @param registroDto DTO con los datos de registro
     * @return Usuario creado exitosamente
     * @throws RuntimeException si el email ya existe o hay datos inválidos
     */
    public Usuario registrarUsuario(RegistroUsuarioDto registroDto) {
        // Verifica que el email no esté ya registrado
        if (usuarioRepositorio.findByEmail(registroDto.getEmail()).isPresent()) {
            throw new RuntimeException("El email ya está registrado en el sistema");
        }

        // Valida que la contraseña tenga una longitud mínima
        if (registroDto.getPassword() == null || registroDto.getPassword().length() < 6) {
            throw new RuntimeException("La contraseña debe tener mínimo 6 caracteres");
        }

        // Crea un nuevo usuario
        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setNombre(registroDto.getNombre());
        nuevoUsuario.setApellidos(registroDto.getApellidos());
        nuevoUsuario.setEmail(registroDto.getEmail());
        
        // IMPORTANTE: Cifra la contraseña antes de guardar
        // BCrypt genera un hash diferente cada vez, incluso para la misma contraseña
        // Esto mejora la seguridad en caso de que la BD sea comprometida
        nuevoUsuario.setContraseña(passwordEncoder.encode(registroDto.getPassword()));
        
        nuevoUsuario.setTelefono(registroDto.getTelefono());
        nuevoUsuario.setDireccionPrincipal(registroDto.getDireccionPrincipal());
        
        // Por defecto, los nuevos usuarios tienen rol de CLIENTE
        // Solo los administradores pueden cambiar este rol
        nuevoUsuario.setRol("ROLE_CLIENTE");
        
        // Registra la fecha actual de registro
        nuevoUsuario.setFechaRegistro(LocalDateTime.now());
        
        // Los nuevos usuarios están activos por defecto
        nuevoUsuario.setEstado("activo");

        // Guarda el usuario en la base de datos
        return usuarioRepositorio.save(nuevoUsuario);
    }

    /**
     * Obtiene un usuario por su email
     * 
     * @param email Email del usuario a buscar
     * @return Optional con el usuario si existe
     */
    public Optional<Usuario> obtenerPorEmail(String email) {
        return usuarioRepositorio.findByEmail(email);
    }

    /**
     * Obtiene un usuario por su ID
     * 
     * @param idUsuario ID del usuario
     * @return Optional con el usuario si existe
     */
    public Optional<Usuario> obtenerPorId(Long idUsuario) {
        return usuarioRepositorio.findById(idUsuario);
    }

    /**
     * Actualiza los datos de un usuario
     * 
     * @param usuario Usuario con los datos actualizados
     * @return Usuario actualizado
     */
    public Usuario actualizarUsuario(Usuario usuario) {
        return usuarioRepositorio.save(usuario);
    }
}
