package com.herramientas.desarrollo.Entidades;

public class Pago {
    private String idPago;                // Identificador único del pago
    private double monto;                 // Monto del pago
    private String moneda;                // Moneda (ej: "USD", "EUR", "MXN")
    private	String fechaPago;     // Fecha y hora del pago
    private String metodoPago;           // Método de pago (ej: "Tarjeta", "PayPal", "Transferencia")
    private String estado;               // Estado del pago (ej: "Completado", "Pendiente", "Fallido")
    private String referencia;           // Referencia de la transacción (opcional)
    private String descripcion;          // Descripción o concepto del pago
    private String idUsuario;            // ID del usuario que realizó el pago
	public String getIdPago() {
		return idPago;
	}
	public void setIdPago(String idPago) {
		this.idPago = idPago;
	}
	public double getMonto() {
		return monto;
	}
	public void setMonto(double monto) {
		this.monto = monto;
	}
	public String getMoneda() {
		return moneda;
	}
	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}
	public String getFechaPago() {
		return fechaPago;
	}
	public void setFechaPago(String fechaPago) {
		this.fechaPago = fechaPago;
	}
	public String getMetodoPago() {
		return metodoPago;
	}
	public void setMetodoPago(String metodoPago) {
		this.metodoPago = metodoPago;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getReferencia() {
		return referencia;
	}
	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}
    
    
    
}
