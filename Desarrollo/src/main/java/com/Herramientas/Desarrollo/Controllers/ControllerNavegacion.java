package com.Herramientas.Desarrollo.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ControllerNavegacion {

	@GetMapping("/")
	public String index() {
		return "inicio";
	}
}
