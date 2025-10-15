package com.herramientas.desarrollo.Entidades;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;

    private String nombre;
    private String apellidos;
    private String email;
    private String contraseña;
    private String telefono;
    private String direccionPrincipal;
    private String rol; // cliente o administrador
    private LocalDateTime fechaRegistro;
    private String estado; // activo / inactivo

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Tarjeta> tarjetas;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Pedido> pedidos;
    // Getters y setters
}
