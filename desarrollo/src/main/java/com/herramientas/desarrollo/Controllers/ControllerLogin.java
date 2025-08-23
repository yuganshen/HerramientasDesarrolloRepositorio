package com.herramientas.desarrollo.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.herramientas.desarrollo.DTOs.DTOUsuario;

@Controller
public class ControllerLogin {
	
	@PostMapping("/login")
    public String login(@ModelAttribute DTOUsuario dto) {
        System.out.println(dto);

        System.out.println("Usuario: " + dto.getUser());
        System.out.println("Contraseña: " + dto.getPassword());

        return "inicio"; 
    }
}
