package com.herramientas.desarrollo.DTOs;

public class UsuarioUpdateDto {
    public String nombre;
    public String apellidos;
    public String telefono;
    public String contraseña; // opcional: si se envía, actualizar contraseña

    public UsuarioUpdateDto() {}

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getApellidos() { return apellidos; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public String getContraseña() { return contraseña; }
    public void setContraseña(String contraseña) { this.contraseña = contraseña; }
}
