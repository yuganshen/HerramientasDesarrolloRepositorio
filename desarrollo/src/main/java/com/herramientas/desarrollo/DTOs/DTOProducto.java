package com.herramientas.desarrollo.DTOs;

import java.time.LocalDateTime;

import com.herramientas.desarrollo.Entidades.Categoria;



public class DTOProducto {
	
    private Long idProducto;
    private String nombre;
    private String descripcion;
    private Double precio;
    private Integer stock;
    private String imagenPrincipal;
    private String marca;
    private LocalDateTime fechaCreacion;
    private String tipoProducto;
    private String estado; 
    private Categoria categoria;
    
	public DTOProducto() {
		
	}
	
	public DTOProducto(Long idProducto, String nombre, String descripcion, Double precio, Integer stock,
			String imagenPrincipal, String marca, LocalDateTime fechaCreacion, String tipoProducto, String estado,
			Categoria categoria) {
		this.idProducto = idProducto;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
		this.stock = stock;
		this.imagenPrincipal = imagenPrincipal;
		this.marca = marca;
		this.fechaCreacion = fechaCreacion;
		this.tipoProducto = tipoProducto;
		this.estado = estado;
		this.categoria = categoria;
	}
	
	public Long getIdProducto() {
		return idProducto;
	}
	public void setIdProducto(Long idProducto) {
		this.idProducto = idProducto;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Double getPrecio() {
		return precio;
	}
	public void setPrecio(Double precio) {
		this.precio = precio;
	}
	public Integer getStock() {
		return stock;
	}
	public void setStock(Integer stock) {
		this.stock = stock;
	}
	public String getImagenPrincipal() {
		return imagenPrincipal;
	}
	public void setImagenPrincipal(String imagenPrincipal) {
		this.imagenPrincipal = imagenPrincipal;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public LocalDateTime getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(LocalDateTime fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public String getTipoProducto() {
		return tipoProducto;
	}
	public void setTipoProducto(String tipoProducto) {
		this.tipoProducto = tipoProducto;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

    
    
}
