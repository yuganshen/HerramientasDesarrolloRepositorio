package com.herramientas.desarrollo.Entidades;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {	
	private int id;
	private String user;
	private String password;
}
