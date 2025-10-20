
package com.herramientas.desarrollo.DTOs;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class RegistroUsuarioDto {

    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 2, max = 30, message = "El nombre debe tener entre 2 y 30 caracteres")
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$", message = "El nombre solo puede contener letras y espacios")
    private String nombre;

    @NotBlank(message = "El apellido es obligatorio")
    @Size(min = 2, max = 30, message = "El apellido debe tener entre 2 y 30 caracteres")
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$", message = "El apellido solo puede contener letras y espacios")
    private String apellido;

    @NotBlank(message = "El usuario es obligatorio")
    @Size(min = 3, max = 20, message = "El usuario debe tener entre 3 y 20 caracteres")
    private String usuario;

    @NotBlank(message = "El correo es obligatorio")
    @Email(message = "El correo debe tener un formato válido")
    private String email;

    @NotBlank(message = "El teléfono es obligatorio")
    @Pattern(regexp = "^[0-9\\s\\-()+]{7,20}$", message = "Formato de teléfono no válido")
    private String telefono;

    @NotBlank(message = "El país es obligatorio")
    private String pais;

    @NotBlank(message = "La contraseña es obligatorio")
    @Size(min = 8, message = "La contraseña debe tener al menos 8 caracteres")
    private String password;

    @NotBlank(message = "Debe confirmar la contraseña")
    private String confirm;

    @NotNull(message = "Debe aceptar los términos y condiciones")
    private Boolean terms;
    
    
    

    public RegistroUsuarioDto(String nombre, String apellido, String usuario, String email, String telefono,
			String pais, String password, String confirm, Boolean terms) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.usuario = usuario;
		this.email = email;
		this.telefono = telefono;
		this.pais = pais;
		this.password = password;
		this.confirm = confirm;
		this.terms = terms;
	}

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getUsuario() {
        return usuario;
    }
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getPais() {
        return pais;
    }
    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirm() {
        return confirm;
    }
    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }

    public Boolean getTerms() {
        return terms;
    }
    public void setTerms(Boolean terms) {
        this.terms = terms;
    }
}   