package com.herramientas.desarrollo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.herramientas.desarrollo.Entidades.Pedido;
import com.herramientas.desarrollo.Repositorios.PedidoRepositorio;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepositorio pedidoRepositorio;

    // Crear o actualizar un pedido
    public Pedido guardarPedido(Pedido pedido) {
        return pedidoRepositorio.save(pedido);
    }

    // Listar todos los pedidos
    public List<Pedido> listarPedidos() {
        return pedidoRepositorio.findAll();
    }

    // Buscar pedido por id
    public Optional<Pedido> obtenerPedidoPorId(Long id) {
        return pedidoRepositorio.findById(id);
    }

    // Actualizar pedido
    public Pedido actualizarPedido(Long id, Pedido pedidoDetalles) {
        return pedidoRepositorio.findById(id).map(pedido -> {
            // aquí actualizas los campos que necesites

            // agrega ms setters segun los campos de la entidad
            return pedidoRepositorio.save(pedido);
        }).orElseThrow(() -> new RuntimeException("Pedido no encontrado con id: " + id));
    }

    // Eliminar pedido
    public void eliminarPedido(Long id) {
        if (pedidoRepositorio.existsById(id)) {
            pedidoRepositorio.deleteById(id);
        } else {
            throw new RuntimeException("Pedido no encontrado con id: " + id);
        }
    }
}
