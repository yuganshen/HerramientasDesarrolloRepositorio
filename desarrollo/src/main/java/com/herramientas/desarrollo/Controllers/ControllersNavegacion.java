package com.herramientas.desarrollo.Controllers;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class ControllersNavegacion {

	
	@GetMapping("/")
	public String inicio() {
		return"Login";
	}

	@GetMapping("/login")
	public String login() {
		return"Login";
	}
	
	



}
