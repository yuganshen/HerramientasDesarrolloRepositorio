package com.herramientas.desarrollo.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.herramientas.desarrollo.DTOs.RegistroUsuarioDto;

@Controller
public class ControllersNavegacion {

	@GetMapping("/")
	public String inicio() {
		return"Login";
	}
	
	@PostMapping("/registrar")
	public String registrarNuevoUsuario(Model model){
		//model.addAttribute("registroUsuarioDto", new RegistroUsuarioDto());
		return"Form_Registro";
	}	
	
	@GetMapping("/carrito")
	public String carrito() {
		return"carrito";
	}
	@GetMapping("/compras")
	public String compras() {
		return"Comprasrealizadas";
	}
	
	@GetMapping("/inicio")
	public String Start() {
		return"inicio";
	}
	
	@GetMapping("/perfil")
	public String perfil() {
		return"Perfilusuario";
	}
	
	@GetMapping("/pago")
	public String pago() {
		return"Pago";
	}
	
	@GetMapping("/gestionproductos")
	public String gestionproductos() {
		return"Gestionproductos";
	}
	
	@GetMapping("/gestionusuarios")
	public String gestionusuarios() {
		return"Gestionusuarios";
	}
	
	@GetMapping("/inicioAdmin")
	public String inicioiAdmin() {
		return"inicioadm";
	}
	@GetMapping("/catalogo")
	public String catalogo() {
		return"catalogo";
	}
	@GetMapping("/Detalle-producto.html")
	public String detalles() {
		return"Detalle-producto";
	}
	@GetMapping("/gestionpedidos")
	public String ga() {
		return"Gestionpedidos";
	}
	
	@GetMapping("/detallecompra")
	public String detallecompra() {
		return"Detalle-compra";
	}
	
	@GetMapping("/monitoressamsung")
	public String monitoressamsung() {
		return"MonitoresSamsung";
	}

	//Nuevos controladores 

}
