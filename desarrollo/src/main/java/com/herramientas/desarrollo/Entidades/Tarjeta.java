package com.herramientas.desarrollo.Entidades;

import java.util.List;
import jakarta.persistence.*;

@Entity
@Table(name = "tarjetas")
public class Tarjeta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTarjeta;

    private String tipo; // crédito / débito / billetera / transferencia
    private String numeroEnmascarado;
    private String titular;
    private String fechaVencimiento;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @OneToMany(mappedBy = "tarjeta", cascade = CascadeType.ALL)
    private List<Pedido> pedidos;

    
	public Tarjeta(Long idTarjeta, String tipo, String numeroEnmascarado, String titular, String fechaVencimiento,
			Usuario usuario, List<Pedido> pedidos) {
		super();
		this.idTarjeta = idTarjeta;
		this.tipo = tipo;
		this.numeroEnmascarado = numeroEnmascarado;
		this.titular = titular;
		this.fechaVencimiento = fechaVencimiento;
		this.usuario = usuario;
		this.pedidos = pedidos;
	}

	public Long getIdTarjeta() {
		return idTarjeta;
	}

	public void setIdTarjeta(Long idTarjeta) {
		this.idTarjeta = idTarjeta;
	}

	public String getFechaVencimiento() {
		return fechaVencimiento;
	}

	public void setFechaVencimiento(String fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	public String getNumeroEnmascarado() {
		return numeroEnmascarado;
	}

	public void setNumeroEnmascarado(String numeroEnmascarado) {
		this.numeroEnmascarado = numeroEnmascarado;
	}

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getTitular() {
		return titular;
	}

	public void setTitular(String titular) {
		this.titular = titular;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

    
	
}




