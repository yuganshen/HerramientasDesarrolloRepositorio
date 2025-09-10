package com.herramientas.desarrollo.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController //para devolver el json en el front de angular
@Controller
@RequestMapping ("/laptos")
public class ControllerSubOpciones {
	  @GetMapping("/dell")
	    public String getDell() {
	        return "Mostrando laptops Dell";
	    }

	    @GetMapping("/hp")
	    public String getHp() {
	        return "Mostrando laptops HP";
	    }

	    @GetMapping("/lenovo")
	    public String getLenovo() {
	        return "Mostrando laptops Lenovo";
	    }

	    @GetMapping("/asus")
	    public String getAsus() {
	        return "Mostrando laptops Asus";
	    }

}
