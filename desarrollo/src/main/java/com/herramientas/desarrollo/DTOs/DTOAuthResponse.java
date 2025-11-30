package com.herramientas.desarrollo.DTOs;

/**
 * DTO para la respuesta exitosa de autenticación.
 * 
 * Se retorna después de un login exitoso.
 * Contiene el token JWT y la información del usuario.
 * 
 * Ejemplo JSON:
 * {
 *   "token": "eyJhbGc...",
 *   "tipoToken": "Bearer",
 *   "email": "usuario@example.com",
 *   "rol": "ROLE_CLIENTE",
 *   "idUsuario": 1,
 *   "nombreUsuario": "Juan Pérez",
 *   "mensaje": "Autenticación exitosa"
 * }
 */
public class DTOAuthResponse {

    /**
     * Token JWT a usar en solicitudes posteriores.
     * Debe ser enviado en el header: Authorization: Bearer {token}
     */
    private String token;

    /**
     * Tipo de token (siempre "Bearer" para JWT).
     */
    private String tipoToken;

    /**
     * Email del usuario autenticado.
     */
    private String email;

    /**
     * Rol del usuario (ej: ROLE_CLIENTE, ROLE_ADMINISTRADOR).
     */
    private String rol;

    /**
     * ID del usuario en la base de datos.
     */
    private Long idUsuario;

    /**
     * Nombre completo del usuario (nombre + apellidos).
     */
    private String nombreUsuario;

    /**
     * Mensaje de éxito.
     */
    private String mensaje;

    // ===== Constructores =====

    public DTOAuthResponse() {
    }

    public DTOAuthResponse(String token, String email, String rol, 
                          Long idUsuario, String nombreUsuario) {
        this.token = token;
        this.tipoToken = "Bearer";
        this.email = email;
        this.rol = rol;
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.mensaje = "Autenticación exitosa";
    }

    public DTOAuthResponse(String token, String tipoToken, String email, String rol,
                          Long idUsuario, String nombreUsuario, String mensaje) {
        this.token = token;
        this.tipoToken = tipoToken;
        this.email = email;
        this.rol = rol;
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.mensaje = mensaje;
    }

    // ===== Getters y Setters =====

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTipoToken() {
        return tipoToken;
    }

    public void setTipoToken(String tipoToken) {
        this.tipoToken = tipoToken;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    @Override
    public String toString() {
        return "DTOAuthResponse{" +
                "token='" + (token != null ? token.substring(0, Math.min(20, token.length())) + "..." : "null") + '\'' +
                ", tipoToken='" + tipoToken + '\'' +
                ", email='" + email + '\'' +
                ", rol='" + rol + '\'' +
                ", idUsuario=" + idUsuario +
                ", nombreUsuario='" + nombreUsuario + '\'' +
                ", mensaje='" + mensaje + '\'' +
                '}';
    }
}
