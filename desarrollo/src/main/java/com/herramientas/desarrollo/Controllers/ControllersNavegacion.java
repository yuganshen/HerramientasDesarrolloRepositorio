package com.herramientas.desarrollo.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
	
	@GetMapping("/producto")
	public String productos() {
		return"productos";
	}
	
	@GetMapping("/mouse")
	public String produ() {
		return"Mouses";
    }
	
	@GetMapping("/impresora")
	public String impre() {
		return"Impresoras";
    }
	
	@GetMapping("/historial")
	public String historial() {
		return"Historial";
    }
	
	@GetMapping("/monitores")
	public String moni() {
		return"Monitores";
    }
}

