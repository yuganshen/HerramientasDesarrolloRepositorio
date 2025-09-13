package com.herramientas.desarrollo.Entidades;

public class Pago {
    private String idPago;                // Identificador único del pago
    private double monto;                 // Monto del pago
    private String moneda;                // Moneda (ej: "USD", "EUR", "MXN")
    private LocalDateTime fechaPago;     // Fecha y hora del pago
    private String metodoPago;           // Método de pago (ej: "Tarjeta", "PayPal", "Transferencia")
    private String estado;               // Estado del pago (ej: "Completado", "Pendiente", "Fallido")
    private String referencia;           // Referencia de la transacción (opcional)
    private String descripcion;          // Descripción o concepto del pago
    private String idUsuario;            // ID del usuario que realizó el pago
}
