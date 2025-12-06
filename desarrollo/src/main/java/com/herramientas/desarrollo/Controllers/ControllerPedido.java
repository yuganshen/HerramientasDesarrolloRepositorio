package com.herramientas.desarrollo.Controllers;

import com.herramientas.desarrollo.DTOs.PedidoRequest;
import com.herramientas.desarrollo.Entidades.DetallePedido;
import com.herramientas.desarrollo.Entidades.Pedido;
import com.herramientas.desarrollo.Entidades.Producto;
import com.herramientas.desarrollo.Entidades.Usuario;
import com.herramientas.desarrollo.Repositorios.PedidoRepositorio;
import com.herramientas.desarrollo.Repositorios.ProductoRepositorio;
import com.herramientas.desarrollo.Repositorios.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pedidos")
@CrossOrigin(origins = "http://localhost:4200")
public class ControllerPedido {

    @Autowired
    private ProductoRepositorio productoRepositorio;

    @Autowired
    private PedidoRepositorio pedidoRepositorio;

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;
    
	@GetMapping("/obtenerPedidos")
	public ResponseEntity<List<Pedido>> obtenerPedidos() {
	    return ResponseEntity.ok(pedidoRepositorio.findAll());
	}
	@PutMapping("/cambiarEstado/{id}")
	public ResponseEntity<Pedido> cambiarEstado(@PathVariable("id") Long id) {
	    Optional<Pedido> pedidoOpt = pedidoRepositorio.findById(id);
	    if (pedidoOpt.isPresent()) {
	        Pedido pedido = pedidoOpt.get();
	        pedido.setEstado("ENTREGADO");
	        pedidoRepositorio.save(pedido);
	        return ResponseEntity.ok(pedido);
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}

    
    @PostMapping
    public ResponseEntity<?> crearPedido(@RequestBody PedidoRequest request) {
        // Validaciones básicas
        if (request == null || request.items == null || request.items.isEmpty()) {
            return ResponseEntity.badRequest().body("El pedido debe contener al menos un item");
        }

        // Obtener usuario autenticado
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || auth.getName() == null) {
            return ResponseEntity.status(401).body("Usuario no autenticado");
        }

        Optional<Usuario> usuarioOpt = usuarioRepositorio.findByEmail(auth.getName());
        if (usuarioOpt.isEmpty()) {
            return ResponseEntity.status(401).body("Usuario no encontrado");
        }
        Usuario usuario = usuarioOpt.get();

        // Construir Pedido y Detalles
        Pedido pedido = new Pedido();
        pedido.setFechaPedido(LocalDateTime.now());
        pedido.setEstado("PENDIENTE");
        pedido.setMetodoPago(request.metodoPago);
        pedido.setTipoComprobante(request.tipoComprobante);
        pedido.setDireccionEnvio(request.direccionEnvio);
        pedido.setTipoEntrega(request.tipoEntrega);
        pedido.setUsuario(usuario);

        List<DetallePedido> detalles = new ArrayList<>();
        double total = 0.0;

        for (PedidoRequest.ItemPedidoRequest item : request.items) {
            Optional<Producto> productoOpt = productoRepositorio.findById(item.productoId);
            if (productoOpt.isEmpty()) {
                return ResponseEntity.badRequest().body("Producto no encontrado: " + item.productoId);
            }
            Producto producto = productoOpt.get();

            DetallePedido detalle = new DetallePedido();
            detalle.setCantidad(item.cantidad != null ? item.cantidad : 1);
            detalle.setPrecioUnitario(producto.getPrecio());
            double subtotal = producto.getPrecio() * detalle.getCantidad();
            detalle.setSubtotal(subtotal);
            detalle.setProducto(producto);
            detalle.setPedido(pedido);

            detalles.add(detalle);
            total += subtotal;
        }

        pedido.setTotal(total);
        pedido.setDetalles(detalles);

        // Guardar pedido (cascade guardará los detalles)
        Pedido pedidoGuardado = pedidoRepositorio.save(pedido);

        // Responder con id del pedido creado (evitar retornar la entidad completa para prevenir recursión JSON)
        java.util.Map<String, Object> resp = new java.util.HashMap<>();
        resp.put("idPedido", pedidoGuardado.getIdPedido());
        resp.put("fechaPedido", pedidoGuardado.getFechaPedido().toString());
        resp.put("total", pedidoGuardado.getTotal());
        return ResponseEntity.ok(resp);
    }
    
    
}
