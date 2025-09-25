package com.herramientas.desarrollo.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.herramientas.desarrollo.DTOs.RegistroUsuarioDto;
import com.herramientas.desarrollo.DTOs.RegistroUsuarioDto;


@Controller
public class ControllersNavegacion {

	@GetMapping("/")
	public String inicio() {
		return"Login";
	}
	
	@GetMapping("/registrar")
	public String registrarNuevoUsuario(Model model){
		model.addAttribute("registroUsuarioDto", new RegistroUsuarioDto());
		return"Form_Registro";
	}	
	
	@GetMapping("/catalogo")
	public String catalogo() {
		return"Catalogo";
	}
	
	@GetMapping("/carro")
	public String carrito() {
		return"Carrito";
	}
	
	@GetMapping("/pantallas")
	public String pantallitas() {
		return"Pantalla";
	}

	@GetMapping("/contactenos")
	public String contactos() {
		return"Contacto";
	}

	@GetMapping("/teclados")
	public String tecladiños() {
		return"teclado";
	}
	
	@GetMapping("/tecladosgamer")
	public String tecladiñosga() {
		return"tecladogamer";
	}

}

