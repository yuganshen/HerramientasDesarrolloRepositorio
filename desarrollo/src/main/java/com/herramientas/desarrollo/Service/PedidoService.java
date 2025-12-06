package com.herramientas.desarrollo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.herramientas.desarrollo.Entidades.Pedido;
import com.herramientas.desarrollo.Entidades.Usuario;
import com.herramientas.desarrollo.Repositorios.PedidoRepositorio;
import com.herramientas.desarrollo.DTOs.PedidoDTO;
import com.herramientas.desarrollo.DTOs.DetallePedidoDTO;


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

    // Listar pedidos de un usuario específico
    public List<Pedido> listarPedidosPorUsuario(Usuario usuario) {
        return pedidoRepositorio.findByUsuario(usuario);
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

    // Cancelar pedido
    public Pedido cancelarPedido(Long id) {
        return pedidoRepositorio.findById(id).map(pedido -> {
            // Solo permitir cancelación si el pedido está en estado PENDIENTE
            if (!"PENDIENTE".equalsIgnoreCase(pedido.getEstado())) {
                throw new RuntimeException("Solo se pueden cancelar pedidos en estado PENDIENTE");
            }
            pedido.setEstado("CANCELADO");
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
    public PedidoDTO convertirADTO(Pedido pedido) {
    PedidoDTO dto = new PedidoDTO();
    dto.idPedido = pedido.getIdPedido();
    dto.fechaPedido = pedido.getFechaPedido();
    dto.total = pedido.getTotal();
    dto.estado = pedido.getEstado();

    dto.detalles = pedido.getDetalles().stream().map(det -> {
        DetallePedidoDTO d = new DetallePedidoDTO();
        d.idProducto = det.getProducto().getIdProducto();
        d.nombreProducto = det.getProducto().getNombre();
        d.cantidad = det.getCantidad();
        d.precioUnitario = det.getPrecioUnitario();
        d.subtotal = det.getSubtotal();
        return d;
    }).toList();

    return dto;
}

}

