package com.herramientas.desarrollo.Entidades;

import java.util.List;
import javax.persistence.*;

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

    
	
}




