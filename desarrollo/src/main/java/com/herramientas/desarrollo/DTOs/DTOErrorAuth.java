package com.herramientas.desarrollo.DTOs;

/**
 * DTO para respuestas de error en autenticación.
 * 
 * Se retorna cuando hay errores en login, registro o validación de token.
 * 
 * Ejemplo JSON:
 * {
 *   "codigoError": 401,
 *   "mensaje": "Credenciales inválidas",
 *   "descripcion": "El email o la contraseña son incorrectos",
 *   "timestamp": 1699000000000
 * }
 */
public class DTOErrorAuth {

    /**
     * Código HTTP del error (400, 401, 403, 500, etc).
     */
    private int codigoError;

    /**
     * Título o resumen del error.
     */
    private String mensaje;

    /**
     * Descripción detallada del error.
     */
    private String descripcion;

    /**
     * Marca de tiempo cuando ocurrió el error.
     */
    private long timestamp;

    // ===== Constructores =====

    public DTOErrorAuth() {
    }

    public DTOErrorAuth(int codigoError, String mensaje, String descripcion) {
        this.codigoError = codigoError;
        this.mensaje = mensaje;
        this.descripcion = descripcion;
        this.timestamp = System.currentTimeMillis();
    }

    public DTOErrorAuth(int codigoError, String mensaje, String descripcion, long timestamp) {
        this.codigoError = codigoError;
        this.mensaje = mensaje;
        this.descripcion = descripcion;
        this.timestamp = timestamp;
    }

    // ===== Getters y Setters =====

    public int getCodigoError() {
        return codigoError;
    }

    public void setCodigoError(int codigoError) {
        this.codigoError = codigoError;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "DTOErrorAuth{" +
                "codigoError=" + codigoError +
                ", mensaje='" + mensaje + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
