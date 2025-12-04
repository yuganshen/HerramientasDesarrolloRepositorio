
package com.herramientas.desarrollo.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.herramientas.desarrollo.DTOs.DTOUsuario;
import com.herramientas.desarrollo.DTOs.RegistroUsuarioDto;

@Controller
public class ControllerLogin {
	

	 
	@PostMapping("/sesion")
    public String recibirSeleccion(@RequestParam(name="opcion") String opcion) {
		if(opcion.equals("admin")) {
			return "redirect:/inicioAdmin";
		}else if(opcion.equals("usuario")){
			return "Catalogo";
		}
        System.out.println("Opción seleccionada: " + opcion);
        return "Login"; // redirige a otra vista o podrías volver al formulario
    }
	
	//Controller de registro de ususarios + validacion de campos
	/*@PostMapping("/registro")
	public String procesarRegistro(
		    @Validated @ModelAttribute RegistroUsuarioDto registroUsuarioDto,
		    BindingResult result,
		    Model model)
 {
        //en caso de un campo erroneo
		if (result.hasErrors()) {
		    model.addAttribute("registroUsuarioDto", registroUsuarioDto);
		    return "Form_Registro";
		}

        // Si pasa las validaciones, imprime en consola
        System.out.println("Nombre: " + registroUsuarioDto.getNombre());
        System.out.println("Apellido: " + registroUsuarioDto.getApellidos());
        System.out.println("Usuario: " + registroUsuarioDto.getUsuario());
        System.out.println("Email: " + registroUsuarioDto.getEmail());
        System.out.println("Teléfono: " + registroUsuarioDto.getTelefono());
        System.out.println("País: " + registroUsuarioDto.getPais());
        System.out.println("Password: " + registroUsuarioDto.getPassword());
        System.out.println("Confirm: " + registroUsuarioDto.getConfirm());

        // En el futuro aquí iría la lógica de guardar en la base de datos
        model.addAttribute("mensaje", "Registro exitoso");

        // Redirige o vuelve a mostrar la vista
        return "Login"; 
    }*/
}
   