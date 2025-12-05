package com.herramientas.desarrollo.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.herramientas.desarrollo.Entidades.Producto;
import com.herramientas.desarrollo.Repositorios.ProductoRepositorio;


@RestController
@RequestMapping("/apiproducto")
@CrossOrigin(origins = "http://localhost:4200") // permite Angular local
public class ControllerProducto {
	
	
	@Autowired
	private ProductoRepositorio productoRepositorio;
	
	@GetMapping("/obtenerProductos")
	public ResponseEntity<List<Producto>> obtenerProductos() {
	    return ResponseEntity.ok(productoRepositorio.findAll());
	}
	
	@GetMapping("/filtrarProductos")
	public ResponseEntity<List<Producto>> filtrarPorTipo(@RequestParam("tipo") String tipo) {
	    List<Producto> lista = productoRepositorio.findByTipoProducto(tipo);
	    return ResponseEntity.ok(lista);
	}
	
	
	
}
