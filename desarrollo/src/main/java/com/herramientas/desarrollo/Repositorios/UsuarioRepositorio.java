package com.herramientas.desarrollo.Repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import com.herramientas.desarrollo.Entidades.Usuario;

import java.util.Optional;

/**
 * Repositorio de acceso a datos para Usuario.
 * 
 * Proporciona métodos para realizar operaciones CRUD en la tabla "usuarios".
 * Spring Data JPA genera automáticamente las implementaciones.
 */
public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {

    /**
     * Busca un usuario por email.
     * 
     * @param email Email del usuario a buscar
     * @return Optional con el usuario si existe, vacío si no
     * 
     * Usado en:
     * - ServicioDetallesUsuario.loadUserByUsername()
     * - ServicioAutenticacion durante login
     */
    Optional<Usuario> findByEmail(String email);
}

