package com.herramientas.desarrollo.Seguridad;

import io.jsonwebtoken.Jwts;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

/**
 * Proveedor de tokens JWT para autenticación.
 * 
 * Esta clase gestiona la creación y validación de tokens JWT (JSON Web Tokens)
 * utilizados para autenticar solicitudes a la API. Los tokens contienen:
 * - Email del usuario (subject)
 * - Rol del usuario (claim personalizado)
 * - Fecha de expiración
 * 
 * Utiliza el algoritmo HS512 (HMAC con SHA-512) para firmar tokens.
 */
@Component
public class JwtTokenProvider {

    @Value("${jwt.secret:miClaveSecretaMuyLargaParaHS512Desarrollo}")
    private String jwtSecret;

    @Value("${jwt.expiration:3600000}")
    private long jwtExpirationMs;

    /**
     * Genera un token JWT con el email y rol del usuario.
     *
     * @param email Email del usuario (se usa como identificador único)
     * @param rol Rol del usuario (ej: ROLE_CLIENTE, ROLE_ADMINISTRADOR)
     * @return Token JWT comprimido como String
     * 
     * Ejemplo:
     * String token = jwtTokenProvider.generarToken("usuario@example.com", "ROLE_CLIENTE");
     */
    public String generarToken(String email, String rol) {
        Date ahora = new Date();
        Date fechaExpiracion = new Date(ahora.getTime() + jwtExpirationMs);

        SecretKey key = Keys.hmacShaKeyFor(jwtSecret.getBytes());

        return Jwts.builder()
                .setSubject(email)  // Email como identificador
                .claim("rol", rol)  // Rol como claim personalizado
                .setIssuedAt(ahora)
                .setExpiration(fechaExpiracion)
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }

    /**
     * Obtiene el email (subject) del token JWT.
     *
     * @param token Token JWT a parsear
     * @return Email del usuario contenido en el token
     * @throws Exception Si el token es inválido o ha expirado
     */
    public String obtenerEmailDelToken(String token) {
        SecretKey key = Keys.hmacShaKeyFor(jwtSecret.getBytes());
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    /**
     * Obtiene el rol del usuario desde el token JWT.
     *
     * @param token Token JWT a parsear
     * @return Rol del usuario (ej: ROLE_CLIENTE, ROLE_ADMINISTRADOR)
     * @throws Exception Si el token es inválido o ha expirado
     */
    public String obtenerRolDelToken(String token) {
        SecretKey key = Keys.hmacShaKeyFor(jwtSecret.getBytes());
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .get("rol", String.class);
    }

    /**
     * Valida que un token JWT sea válido (no expirado, firma correcta).
     *
     * @param token Token JWT a validar
     * @return true si el token es válido, false si está expirado o inválido
     */
    public boolean validarToken(String token) {
        try {
            SecretKey key = Keys.hmacShaKeyFor(jwtSecret.getBytes());
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            // Token inválido o expirado
            return false;
        }
    }
}
