package com.herramientas.desarrollo.Entidades;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPedido;

    private LocalDateTime fechaPedido;
    private Double total;
    private String estado; // pendiente, enviado, entregado, cancelado
    private String metodoPago;
    private String tipoComprobante; // boleta / factura
    private String direccionEnvio;
    private String tipoEntrega; // delivery / recojo

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "tarjeta_id")
    private Tarjeta tarjeta;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetallePedido> detalles;

	public Pedido() {
		// Constructor por defecto requerido por JPA
	}

	public Pedido(Long idPedido, LocalDateTime fechaPedido, Double total, String estado, String metodoPago,
			String tipoComprobante, String direccionEnvio, String tipoEntrega, Usuario usuario, Tarjeta tarjeta,
			List<DetallePedido> detalles) {
		super();
		this.idPedido = idPedido;
		this.fechaPedido = fechaPedido;
		this.total = total;
		this.estado = estado;
		this.metodoPago = metodoPago;
		this.tipoComprobante = tipoComprobante;
		this.direccionEnvio = direccionEnvio;
		this.tipoEntrega = tipoEntrega;
		this.usuario = usuario;
		this.tarjeta = tarjeta;
		this.detalles = detalles;
	}

	public Long getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(Long idPedido) {
		this.idPedido = idPedido;
	}

	public LocalDateTime getFechaPedido() {
		return fechaPedido;
	}

	public void setFechaPedido(LocalDateTime fechaPedido) {
		this.fechaPedido = fechaPedido;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getMetodoPago() {
		return metodoPago;
	}

	public void setMetodoPago(String metodoPago) {
		this.metodoPago = metodoPago;
	}

	public String getTipoComprobante() {
		return tipoComprobante;
	}

	public void setTipoComprobante(String tipoComprobante) {
		this.tipoComprobante = tipoComprobante;
	}

	public String getDireccionEnvio() {
		return direccionEnvio;
	}

	public void setDireccionEnvio(String direccionEnvio) {
		this.direccionEnvio = direccionEnvio;
	}

	public String getTipoEntrega() {
		return tipoEntrega;
	}

	public void setTipoEntrega(String tipoEntrega) {
		this.tipoEntrega = tipoEntrega;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Tarjeta getTarjeta() {
		return tarjeta;
	}

	public void setTarjeta(Tarjeta tarjeta) {
		this.tarjeta = tarjeta;
	}

	public List<DetallePedido> getDetalles() {
		return detalles;
	}

	public void setDetalles(List<DetallePedido> detalles) {
		this.detalles = detalles;
	}

    
}


