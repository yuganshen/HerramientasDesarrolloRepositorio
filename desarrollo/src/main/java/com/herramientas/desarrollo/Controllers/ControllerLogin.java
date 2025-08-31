package com.herramientas.desarrollo.Controllers;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.herramientas.desarrollo.DTOs.DTOUsuario;
import com.herramientas.desarrollo.DTOs.RegistroUsuarioDto;

@Controller
public class ControllerLogin {
	
	@PostMapping("/login")
    public String login(@ModelAttribute DTOUsuario dto) {
        System.out.println(dto);
        return "inicio"; 
    }
	 
	
	//Controller de registro de ususarios + validacion de campos
	@PostMapping("/registro")
    public String procesarRegistro(
            @Valid RegistroUsuarioDto registroUsuarioDto,
            BindingResult result,
            Model model) {

        if (result.hasErrors()) {
            // Si hay errores, devuelve al formulario mostrando los mensajes
            return "Form_Registro";
        }

        // Si pasa las validaciones, imprime en consola
        System.out.println("Nombre: " + registroUsuarioDto.getNombre());
        System.out.println("Apellido: " + registroUsuarioDto.getApellido());
        System.out.println("Usuario: " + registroUsuarioDto.getUsuario());
        System.out.println("Email: " + registroUsuarioDto.getEmail());
        System.out.println("Teléfono: " + registroUsuarioDto.getTelefono());
        System.out.println("País: " + registroUsuarioDto.getPais());
        System.out.println("Password: " + registroUsuarioDto.getPassword());
        System.out.println("Confirm: " + registroUsuarioDto.getConfirm());
        System.out.println("Acepta términos: " + registroUsuarioDto.getTerms());

        // En el futuro aquí iría la lógica de guardar en la base de datos
        model.addAttribute("mensaje", "Registro exitoso");

        // Redirige o vuelve a mostrar la vista
        return "Login"; 
    }
}
