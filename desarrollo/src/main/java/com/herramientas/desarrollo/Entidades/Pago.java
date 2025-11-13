package com.herramientas.desarrollo.Entidades;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Pago {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPago;                
    private double monto;                 
    private String moneda;                
    private	String fechaPago;     
    private String metodoPago;           
    private String estado;               
    private String referencia;           
    private String descripcion;          
    private String idUsuario;            

    
	public Pago(Long idPago, double monto, String moneda, String fechaPago, String metodoPago, String estado,
			String referencia, String descripcion, String idUsuario) {
		super();
		this.idPago = idPago;
		this.monto = monto;
		this.moneda = moneda;
		this.fechaPago = fechaPago;
		this.metodoPago = metodoPago;
		this.estado = estado;
		this.referencia = referencia;
		this.descripcion = descripcion;
		this.idUsuario = idUsuario;
	}
	
	public Long getIdPago() {
		return idPago;
	}
	public void setIdPago(Long idPago) {
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



