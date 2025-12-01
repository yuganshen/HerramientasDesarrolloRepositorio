package com.herramientas.desarrollo.Entidades;

import jakarta.persistence.*;

/**
 * Entidad JPA para gestionar roles del sistema.
 * 
 * Los roles definen qué permisos tiene cada usuario:
 * - ROLE_CLIENTE: Usuario común que compra productos
 * - ROLE_ADMINISTRADOR: Usuario que gestiona la plataforma
 */
@Entity
@Table(name = "roles")
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rol")
    private Long idRol;

    /**
     * Nombre único del rol (ej: ROLE_CLIENTE, ROLE_ADMINISTRADOR).
     * Se usa para referencias en el código y en la base de datos.
     */
    @Column(name = "nombre", nullable = false, unique = true, length = 50)
    private String nombre;

    /**
     * Descripción del rol para documentación.
     */
    @Column(name = "descripcion", length = 500)
    private String descripcion;

    /**
     * Indica si el rol está activo.
     * Roles inactivos no pueden asignarse a usuarios nuevos.
     */
    @Column(name = "activo", nullable = false)
    private boolean activo = true;

    // ===== Constructores =====

    /**
     * Constructor vacío requerido por JPA.
     */
    public Rol() {
    }

    /**
     * Constructor con los parámetros principales.
     *
     * @param nombre Nombre del rol (ej: ROLE_CLIENTE)
     * @param descripcion Descripción del rol
     */
    public Rol(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.activo = true;
    }

    /**
     * Constructor completo.
     *
     * @param nombre Nombre del rol
     * @param descripcion Descripción del rol
     * @param activo Si está activo
     */
    public Rol(String nombre, String descripcion, boolean activo) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.activo = activo;
    }

    // ===== Getters y Setters =====

    public Long getIdRol() {
        return idRol;
    }

    public void setIdRol(Long idRol) {
        this.idRol = idRol;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    @Override
    public String toString() {
        return "Rol{" +
                "idRol=" + idRol +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", activo=" + activo +
                '}';
    }
}
