package com.herramientas.desarrollo.Service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.herramientas.desarrollo.Entidades.Tarjeta;
import com.herramientas.desarrollo.Repositorios.TarjetaRepositorio;
import com.herramientas.desarrollo.Repositorios.UsuarioRepositorio;

@Service
@Transactional
public class TarjetaServiceImpl implements TarjetaService {

    @Autowired
    private TarjetaRepositorio tarjetaRepository;

    @Autowired
    private UsuarioRepositorio usuarioRepository; // Necesario si asocias usuario

    @Override
    public Tarjeta guardarTarjeta(Tarjeta tarjeta) {
        return tarjetaRepository.save(tarjeta);
    }

    @Override
    public Tarjeta obtenerTarjetaPorId(Long idTarjeta) {
        return tarjetaRepository.findById(idTarjeta)
                .orElseThrow(() -> new RuntimeException("Tarjeta no encontrada"));
    }

    @Override
    public List<Tarjeta> listarTarjetas() {
        return tarjetaRepository.findAll();
    }
/*
    @Override
    public List<Tarjeta> listarTarjetasPorUsuario(Long idUsuario) {
        return tarjetaRepository.findByUsuarioId(idUsuario);
    }
*/
    @Override
    public Tarjeta actualizarTarjeta(Long idTarjeta, Tarjeta tarjetaActualizada) {

        Tarjeta tarjetaExistente = obtenerTarjetaPorId(idTarjeta);

        tarjetaExistente.setTipo(tarjetaActualizada.getTipo());
        tarjetaExistente.setNumeroEnmascarado(tarjetaActualizada.getNumeroEnmascarado());
        tarjetaExistente.setTitular(tarjetaActualizada.getTitular());
        tarjetaExistente.setFechaVencimiento(tarjetaActualizada.getFechaVencimiento());

        // Si deseas actualizar el usuario asociado:
        if (tarjetaActualizada.getUsuario() != null) {
            tarjetaExistente.setUsuario(tarjetaActualizada.getUsuario());
        }

        return tarjetaRepository.save(tarjetaExistente);
    }

    @Override
    public void eliminarTarjeta(Long idTarjeta) {
        if (!tarjetaRepository.existsById(idTarjeta)) {
            throw new RuntimeException("La tarjeta no existe");
        }
        tarjetaRepository.deleteById(idTarjeta);
    }
}
