package com.herramientas.desarrollo.DTOs;

import java.util.List;

public class PedidoRequest {
    public String direccionEnvio;
    public String tipoEntrega;
    public String metodoPago;
    public String tipoComprobante;
    public List<ItemPedidoRequest> items;

    public static class ItemPedidoRequest {
        public Long productoId;
        public Integer cantidad;
    }

    public PedidoRequest() {}
}
