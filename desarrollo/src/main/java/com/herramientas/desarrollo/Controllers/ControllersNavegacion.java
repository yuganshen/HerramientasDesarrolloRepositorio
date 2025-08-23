package com.herramientas.desarrollo.Controllers;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class ControllersNavegacion {

	
	@GetMapping("/")
	public String inicio() {
		return"inicio";
	}
	@GetMapping("/inicio")
	public String causa() {
		return"inicio";
	}
	@GetMapping("/login")
	public String login() {
		return"Login";
	}
	
	
	


}
