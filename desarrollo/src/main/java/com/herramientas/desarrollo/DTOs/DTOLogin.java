package com.herramientas.desarrollo.DTOs;

/**
 * DTO para recibir credenciales de login.
 * 
 * Se usa para deserializar el JSON enviado desde Angular.
 * Ejemplo JSON:
 * {
 *   "email": "usuario@example.com",
 *   "contraseña": "miContraseña123"
 * }
 */
public class DTOLogin {

    /**
     * Email del usuario (será buscado en la BD).
     */
    private String email;

    /**
     * Contraseña del usuario en texto plano.
     * Spring Security la comparará con el hash en la BD.
     */
    private String contraseña;

    // ===== Constructores =====

    public DTOLogin() {
    }

    public DTOLogin(String email, String contraseña) {
        this.email = email;
        this.contraseña = contraseña;
    }

    // ===== Getters y Setters =====

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    @Override
    public String toString() {
        return "DTOLogin{" +
                "email='" + email + '\'' +
                ", contraseña='" + (contraseña != null ? "***" : "null") + '\'' +
                '}';
    }
}
