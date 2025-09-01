package com.herramientas.desarrollo.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping ("/laptos")
public class ControllerSubOpciones {

  @GetMapping("/marca")
  public String marca(){
    return "marcas";
  }

}
