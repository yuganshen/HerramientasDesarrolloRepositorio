package com.Herramientas.Desarrollo.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ControllerNavegacion {

	@GetMapping("/")
	public String index() {
		return "inicio";
	}
	@GetMapping("/hola")
	public String hola() {
		return "inicio";
	}
	@GetMapping("/Login")
	public String login() {
		return "Login";
	}
	@GetMapping("/inicio")
	public String login() {
		return "menu";
	}
	
}
