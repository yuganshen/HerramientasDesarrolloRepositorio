package com.herramientas.desarrollo.DTOs;

public class DTOPedido {
	public int idPedido;
	public int idUsuario;
	public int idProducto;
	public String estado;
	public double total;
	public String fecha;
	
	
	public DTOPedido(int idPedido, int idUsuario, int idProducto, String estado, double total, String fecha) {
		super();
		this.idPedido = idPedido;
		this.idUsuario = idUsuario;
		this.idProducto = idProducto;
		this.estado = estado;
		this.total = total;
		this.fecha = fecha;
	}
	
	public int getIdPedido() {
		return idPedido;
	}
	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}
	public int getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	public int getIdProducto() {
		return idProducto;
	}
	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	
	

}
