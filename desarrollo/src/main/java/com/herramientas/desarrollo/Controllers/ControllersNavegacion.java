package com.herramientas.desarrollo.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
//import com.herramientas.desarrollo.DTOs.RegistroUsuarioDto;


@Controller
public class ControllersNavegacion {

	@GetMapping("/")
	public String inicio() {
		return"inicio";
	}
	
	@GetMapping("/navbar")
	public String navegacion() {
		return"NavBar";
	}
/*
	@GetMapping("/registrar")
	public String registrarNuevoUsuario(Model model){
		model.addAttribute("registroUsuarioDto", new RegistroUsuarioDto());
		return"Form_Registro";
	}	*/
}
