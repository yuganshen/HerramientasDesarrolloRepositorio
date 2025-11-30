package com.herramientas.desarrollo.Seguridad;

import com.herramientas.desarrollo.Entidades.Usuario;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

/**
 * Implementación personalizada de UserDetails de Spring Security.
 * 
 * Adapta la entidad Usuario de la aplicación al contrato que
 * Spring Security espera (UserDetails).
 * 
 * Esto permite que el objeto Usuario sea utilizado directamente
 * en el contexto de seguridad de Spring.
 */
public class DetallesUsuarioCustom implements UserDetails {

    private Usuario usuario;

    public DetallesUsuarioCustom(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * Retorna las autoridades (roles) del usuario.
     * Spring Security usa esto para determinar permisos.
     *
     * @return Colección con los roles del usuario
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Obtener el rol del usuario (ej: "ROLE_CLIENTE")
        String rol = usuario.getRol();
        
        // Asegurar que tenga el prefijo "ROLE_"
        if (!rol.startsWith("ROLE_")) {
            rol = "ROLE_" + rol;
        }
        
        // Convertir a GrantedAuthority y retornar como colección
        return Collections.singleton(new SimpleGrantedAuthority(rol));
    }

    /**
     * Retorna la contraseña del usuario (cifrada con BCrypt).
     * Spring Security la usa para validar credenciales en login.
     *
     * @return Contraseña cifrada del usuario
     */
    @Override
    public String getPassword() {
        return usuario.getContraseña();
    }

    /**
     * Retorna el nombre de usuario (en este caso, el email).
     * Spring Security lo usa como identificador único.
     *
     * @return Email del usuario
     */
    @Override
    public String getUsername() {
        return usuario.getEmail();
    }

    /**
     * Indica si la cuenta ha expirado.
     * En esta implementación, siempre retorna true.
     *
     * @return true si la cuenta NO ha expirado
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Indica si la cuenta está bloqueada.
     * En esta implementación, siempre retorna true.
     *
     * @return true si la cuenta NO está bloqueada
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Indica si las credenciales han expirado.
     * En esta implementación, siempre retorna true.
     *
     * @return true si las credenciales NO han expirado
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Indica si la cuenta está habilitada.
     * Verifica el estado del usuario en la BD.
     *
     * @return true si el usuario está activo
     */
    @Override
    public boolean isEnabled() {
        return "activo".equals(usuario.getEstado());
    }

    // ===== Métodos auxiliares =====

    /**
     * Retorna el usuario original.
     * Útil para acceder a información adicional del usuario.
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * Retorna el ID del usuario.
     */
    public Long getIdUsuario() {
        return usuario.getIdUsuario();
    }

    /**
     * Retorna el nombre completo del usuario.
     */
    public String getNombreCompleto() {
        return usuario.getNombre() + " " + usuario.getApellidos();
    }
}
