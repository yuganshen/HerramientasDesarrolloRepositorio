package com.herramientas.desarrollo.Repositorios;

import org.springframework.data.jpa.repository.JpaRepository;


import com.herramientas.desarrollo.Entidades.Pedido;
public interface PagoRepositorio extends JpaRepository<Pago,Long>{

}
