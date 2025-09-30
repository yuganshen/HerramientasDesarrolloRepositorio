package com.herramientas.desarrollo.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DTOUsuario {
	private String nombre;
    private String apellido;
    private String usuario;
    private String email;
    private String telefono;
    private String pais;
    private String password;
}
