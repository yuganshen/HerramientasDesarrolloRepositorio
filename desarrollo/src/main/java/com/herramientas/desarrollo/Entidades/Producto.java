package com.herramientas.desarrollo.Entidades;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "productos")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProducto;

    private String nombre;
    private String descripcion;
    private Double precio;
    private Integer stock;
    private String imagenPrincipal;
    private String marca;
    private String estado; // activo / inactivo
    private LocalDateTime fechaCreacion;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;
    // Getters y setters
}




