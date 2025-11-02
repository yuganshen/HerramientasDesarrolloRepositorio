package com.herramientas.desarrollo.Entidades;

import javax.persistence.*;

@Entity
@Table(name = "detalles_pedido")
public class DetallePedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDetallePedido;

    private int cantidad;
    private Double precioUnitario;
    private Double subtotal;

    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "producto_id")
    private Producto producto;

	public Long getIdDetallePedido() {
		return idDetallePedido;
	}

	public void setIdDetallePedido(Long idDetallePedido) {
		this.idDetallePedido = idDetallePedido;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

    
}



	