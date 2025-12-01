package com.herramientas.desarrollo.Service;

import com.herramientas.desarrollo.DTOs.DTOLogin;
import com.herramientas.desarrollo.DTOs.DTOAuthResponse;
import com.herramientas.desarrollo.Entidades.Usuario;
import com.herramientas.desarrollo.Repositorios.UsuarioRepositorio;
import com.herramientas.desarrollo.Seguridad.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

/**
 * Servicio de autenticación.
 * 
 * Maneja la lógica de validación de credenciales y generación de tokens JWT.
 * 
 * Flujo:
 * 1. Recibir email y contraseña
 * 2. Validar contra la base de datos
 * 3. Si son válidos, generar token JWT
 * 4. Retornar token y datos del usuario
 */
@Service
public class ServicioAutenticacion {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    /**
     * Autentica un usuario y genera un token JWT.
     * 
     * @param dtoLogin Credenciales (email y contraseña)
     * @return DTOAuthResponse con token y datos del usuario
     * @throws RuntimeException Si las credenciales son inválidas
     * 
     * Proceso:
     * 1. Crear token de autenticación con las credenciales
     * 2. El AuthenticationManager valida contra la BD (BCrypt)
     * 3. Si es válido, generar JWT
     * 4. Retornar respuesta con token
     */
    public DTOAuthResponse autenticar(DTOLogin dtoLogin) {
        try {
            // Crear token de autenticación con credenciales
            UsernamePasswordAuthenticationToken token = 
                new UsernamePasswordAuthenticationToken(
                    dtoLogin.getEmail(),
                    dtoLogin.getContraseña()
                );

            // Validar credenciales contra la BD
            // El AuthenticationManager usa:
            // - ServicioDetallesUsuario para buscar el usuario por email
            // - BCryptPasswordEncoder para validar la contraseña
            Authentication autenticacion = authenticationManager.authenticate(token);

            // Si llegamos aquí, la autenticación fue exitosa
            // Obtener usuario de la BD
            Usuario usuario = usuarioRepositorio.findByEmail(dtoLogin.getEmail())
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

            // Generar token JWT
            String jwtToken = jwtTokenProvider.generarToken(
                usuario.getEmail(),
                usuario.getRol()
            );

            // Preparar nombre completo
            String nombreCompleto = usuario.getNombre() + " " + usuario.getApellidos();

            // Retornar respuesta con token
            return new DTOAuthResponse(
                jwtToken,
                "Bearer",
                usuario.getEmail(),
                usuario.getRol(),
                usuario.getIdUsuario(),
                nombreCompleto,
                "Autenticación exitosa"
            );

        } catch (AuthenticationException e) {
            // Credenciales inválidas
            throw new RuntimeException("Email o contraseña incorrectos: " + e.getMessage());
        }
    }

    /**
     * Valida si un token JWT es válido.
     * 
     * @param token Token a validar
     * @return true si el token es válido, false si está expirado o corrupto
     */
    public boolean validarToken(String token) {
        return jwtTokenProvider.validarToken(token);
    }

    /**
     * Obtiene el email del usuario desde un token JWT.
     * 
     * @param token Token JWT
     * @return Email contenido en el token
     */
    public String obtenerEmailDelToken(String token) {
        return jwtTokenProvider.obtenerEmailDelToken(token);
    }
}
