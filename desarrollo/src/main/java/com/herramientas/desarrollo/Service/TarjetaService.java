package com.herramientas.desarrollo.Service;

import java.util.List;

import com.herramientas.desarrollo.Entidades.Tarjeta;

public interface TarjetaService {

    Tarjeta guardarTarjeta(Tarjeta tarjeta);

    Tarjeta obtenerTarjetaPorId(Long idTarjeta);

    List<Tarjeta> listarTarjetas();

    //List<Tarjeta> listarTarjetasPorUsuario(Long idUsuario);

    Tarjeta actualizarTarjeta(Long idTarjeta, Tarjeta tarjetaActualizada);

    void eliminarTarjeta(Long idTarjeta);
}
