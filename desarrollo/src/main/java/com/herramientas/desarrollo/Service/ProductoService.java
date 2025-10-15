package com.herramientas.desarrollo.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.herramientas.desarrollo.Entidades.Producto;
import com.herramientas.desarrollo.Repositorios.ProductoRepositorio;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepositorio productoRepository;

    public List<Producto> listarProductos() {
        return productoRepository.findAll();
    }

    public Producto guardarProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    public void eliminarProducto(Long id) {
        if (productoRepository.existsById(id)) {
            productoRepository.deleteById(id);
        } else {
            throw new RuntimeException("El producto con ID " + id + " no existe.");
        }
    }
    public Producto actualizarProducto(Long id, Producto productoActualizado) {
        return productoRepository.findById(id)
                .map(productoExistente -> {
                    return productoRepository.save(productoExistente);
                })
                .orElseThrow(() -> new RuntimeException("No se encontró el producto con ID " + id));
    }
}