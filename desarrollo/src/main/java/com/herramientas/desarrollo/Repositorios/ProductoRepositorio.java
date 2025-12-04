package com.herramientas.desarrollo.Repositorios;

import com.herramientas.desarrollo.Entidades.Producto;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepositorio extends JpaRepository<Producto, Long> {
	
	List<Producto> findByTipoProducto(String tipo);
}
