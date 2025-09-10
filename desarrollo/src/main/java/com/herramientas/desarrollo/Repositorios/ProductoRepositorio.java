package com.herramientas.desarrollo.Repositorios;

import com.herramientas.desarrollo.Entidades.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepositorio extends JpaRepository<Producto, Long> {
}
