package com.herramientas.desarrollo.Repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import com.herramientas.desarrollo.Entidades.Rol;

import java.util.Optional;

/**
 * Repositorio de acceso a datos para Rol.
 * 
 * Proporciona métodos para realizar operaciones CRUD en la tabla "roles".
 * Spring Data JPA genera automáticamente las implementaciones.
 */
public interface RolRepositorio extends JpaRepository<Rol, Long> {

    /**
     * Busca un rol por nombre.
     * 
     * @param nombre Nombre del rol a buscar (ej: ROLE_CLIENTE)
     * @return Optional con el rol si existe, vacío si no
     * 
     * Usado en:
     * - InicializadorDatos para verificar si roles ya existen
     */
    Optional<Rol> findByNombre(String nombre);
}
