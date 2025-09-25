package com.herramientas.desarrollo.Repositorios;

import org.springframework.data.jpa.repository.JpaRepository;


import com.herramientas.desarrollo.Entidades.Pedido;

public interface PedidoRepositorio extends JpaRepository<Pedido, Long>{

}
