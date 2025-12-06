package com.herramientas.desarrollo.DTOs;

import java.time.LocalDateTime;
import java.util.List;
import com.herramientas.desarrollo.DTOs.DetallePedidoDTO;



public class PedidoDTO {
    public Long idPedido;
    public LocalDateTime fechaPedido; // ← corregido
    public Double total;
    public String estado;
    public List<DetallePedidoDTO> detalles;
}
