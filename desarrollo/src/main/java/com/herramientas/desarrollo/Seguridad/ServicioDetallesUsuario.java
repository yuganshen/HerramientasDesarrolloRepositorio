package com.herramientas.desarrollo.Seguridad;

import com.herramientas.desarrollo.Entidades.Usuario;
import com.herramientas.desarrollo.Repositorios.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Servicio que carga detalles del usuario para autenticación.
 * 
 * Implementa UserDetailsService de Spring Security.
 * Se encarga de buscar usuarios en la base de datos por email
 * y adaptarlos al formato que Spring Security espera.
 */
@Service
public class ServicioDetallesUsuario implements UserDetailsService {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    /**
     * Carga un usuario por su email (username en este caso).
     * Este método es llamado por Spring Security durante la autenticación.
     *
     * @param email Email del usuario a buscar
     * @return UserDetails con la información del usuario
     * @throws UsernameNotFoundException Si el usuario no existe en la BD
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Buscar usuario por email en la base de datos
        Usuario usuario = usuarioRepositorio.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(
                    "Usuario no encontrado con email: " + email
                ));

        // Adaptar Usuario a DetallesUsuarioCustom (que implementa UserDetails)
        return new DetallesUsuarioCustom(usuario);
    }
}
