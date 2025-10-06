package com.herramientas.desarrollo.Service;

import com.herramientas.desarrollo.Entidades.Pago;
import com.herramientas.desarrollo.Repositorios.PagoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PagoService {

    @Autowired
    private PagoRepositorio pagoRepositorio;

    // ✅ Crear o guardar un pago
    public Pago guardarPago(Pago pago) {
        return pagoRepositorio.save(pago);
    }

    // ✅ Listar todos los pagos
    public List<Pago> listarPagos() {
        return pagoRepositorio.findAll();
    }

    // ✅ Buscar pago por ID
    public Optional<Pago> obtenerPagoPorId(Long id) {
        return pagoRepositorio.findById(id);
    }

    // ✅ Actualizar pago
    public Pago actualizarPago(Long id, Pago pagoDetalles) {
        return pagoRepositorio.findById(id).map(pago -> {
            pago.setMonto(pagoDetalles.getMonto());
            pago.setMetodoPago(pagoDetalles.getMetodoPago());
            pago.setFechaPago(pagoDetalles.getFechaPago());
            pago.setEstado(pagoDetalles.getEstado());
            // 🔹 Agrega más setters según los campos de tu entidad Pago
            return pagoRepositorio.save(pago);
        }).orElseThrow(() -> new RuntimeException("Pago no encontrado con ID: " + id));
    }

    // ✅ Eliminar pago
    public void eliminarPago(Long id) {
        if (pagoRepositorio.existsById(id)) {
            pagoRepositorio.deleteById(id);
        } else {
            throw new RuntimeException("Pago no encontrado con ID: " + id);
        }
    }
}