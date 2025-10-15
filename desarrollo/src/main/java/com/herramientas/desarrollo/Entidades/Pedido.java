package com.herramientas.desarrollo.Entidades;

import javax.persistence.*;


import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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

    // Getters y setters
}


