package com.herramientas.desarrollo.Seguridad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * Filtro JWT que se ejecuta en cada solicitud HTTP.
 * 
 * Este filtro:
 * 1. Extrae el token JWT del header "Authorization"
 * 2. Valida que el token sea válido (no esté expirado)
 * 3. Carga los detalles del usuario usando el email del token
 * 4. Configura el contexto de seguridad de Spring
 * 
 * Se ejecuta una sola vez por solicitud (OncePerRequestFilter)
 * antes de que llegue al controlador.
 */
@Component
public class FiltroJwt extends OncePerRequestFilter {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private ServicioDetallesUsuario servicioDetallesUsuario;

    /**
     * Método que se ejecuta en cada solicitud HTTP.
     * 
     * @param request Solicitud HTTP
     * @param response Respuesta HTTP
     * @param filterChain Cadena de filtros
     * @throws ServletException Si ocurre un error en el servlet
     * @throws IOException Si ocurre un error de I/O
     */
    /*@Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
    	String path = request.getRequestURI();


        try {
            // Extraer token del header "Authorization: Bearer {token}"
            String token = extraerTokenDelRequest(request);

            // Si el token existe y es válido
            if (StringUtils.hasText(token) && jwtTokenProvider.validarToken(token)) {
                // Obtener email del usuario desde el token
                String email = jwtTokenProvider.obtenerEmailDelToken(token);

                // Cargar detalles del usuario de la base de datos
                UserDetails userDetails = servicioDetallesUsuario.loadUserByUsername(email);

                // Crear token de autenticación
                UsernamePasswordAuthenticationToken authentication = 
                    new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                    );

                // Agregar detalles de la solicitud
                authentication.setDetails(
                    new WebAuthenticationDetailsSource().buildDetails(request)
                );

                // Configurar el contexto de seguridad
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception e) {
            // Si hay error al procesar el token, simplemente continúa sin autenticación
            logger.error("No se pudo establecer autenticación del usuario en la cadena de seguridad", e);
        }

        // Continuar con la siguiente cadena de filtros
        filterChain.doFilter(request, response);
    }*/
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String path = request.getRequestURI();

        // 🔓 Permitir rutas públicas sin token
        if (path.startsWith("/api/auth/login") ||
            path.startsWith("/api/auth/registro") ||
            path.startsWith("/api/auth/validar-token")) {

            filterChain.doFilter(request, response);
            return;
        }

        try {
            // Extraer token del header
            String token = extraerTokenDelRequest(request);

            // Si el token existe y es válido
            if (StringUtils.hasText(token) && jwtTokenProvider.validarToken(token)) {

                String email = jwtTokenProvider.obtenerEmailDelToken(token);

                UserDetails userDetails = servicioDetallesUsuario.loadUserByUsername(email);

                UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities()
                    );

                authentication.setDetails(
                    new WebAuthenticationDetailsSource().buildDetails(request)
                );

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }

        } catch (Exception e) {
            logger.error("No se pudo establecer autenticación del usuario en la cadena de seguridad", e);
        }

        filterChain.doFilter(request, response);
    }

    /**
     * Extrae el token JWT del header "Authorization: Bearer {token}".
     *
     * @param request Solicitud HTTP
     * @return Token JWT sin el prefijo "Bearer ", o null si no existe
     */
    private String extraerTokenDelRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);  // Remover "Bearer " (7 caracteres)
        }
        
        return null;
    }
}
