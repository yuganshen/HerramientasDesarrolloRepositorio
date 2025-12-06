package com.herramientas.desarrollo.Repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.herramientas.desarrollo.Entidades.Pedido;
import com.herramientas.desarrollo.Entidades.Usuario;
import java.util.List;

public interface PedidoRepositorio extends JpaRepository<Pedido, Long>{
    
    /**
     * Obtener todos los pedidos de un usuario específico
     */
    List<Pedido> findByUsuario(Usuario usuario);
}

