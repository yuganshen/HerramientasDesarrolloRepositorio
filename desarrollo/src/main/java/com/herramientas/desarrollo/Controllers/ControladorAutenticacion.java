package com.herramientas.desarrollo.Controllers;

import com.herramientas.desarrollo.DTOs.DTOLogin;

import com.herramientas.desarrollo.DTOs.DTOAuthResponse;
import com.herramientas.desarrollo.DTOs.UsuarioPerfilDTO;
import com.herramientas.desarrollo.DTOs.DTOErrorAuth;
import com.herramientas.desarrollo.DTOs.RegistroUsuarioDto;
import com.herramientas.desarrollo.Entidades.Usuario;
import com.herramientas.desarrollo.Service.ServicioAutenticacion;
import com.herramientas.desarrollo.Service.ServicioRegistroUsuario;
import com.herramientas.desarrollo.Seguridad.JwtTokenProvider;
import com.herramientas.desarrollo.DTOs.UsuarioUpdateDto;
import com.herramientas.desarrollo.Service.ServicioRegistroUsuario;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Controlador REST para Autenticación y Autorización
 * 
 * Endpoints disponibles:
 * - POST /api/auth/login - Autentica un usuario
 * - POST /api/auth/registro - Registra un nuevo usuario
 * - POST /api/auth/validar-token - Valida un token JWT
 * - GET /api/auth/info - Obtiene info del usuario autenticado (PROTEGIDO)
 */
@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = {"http://localhost:4200", "http://127.0.0.1:4200"})
public class ControladorAutenticacion {

    @Autowired
    private ServicioAutenticacion servicioAutenticacion;

    @Autowired
    private ServicioRegistroUsuario servicioRegistroUsuario;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * POST /api/auth/login
     * Autentica un usuario y retorna un token JWT
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody DTOLogin dtoLogin) {
        try {
            if (!StringUtils.hasText(dtoLogin.getEmail()) || 
                !StringUtils.hasText(dtoLogin.getContraseña())) {
                
                return ResponseEntity.badRequest().body(new DTOErrorAuth(
                    400,
                    "Datos incompletos",
                    "Email y contraseña son requeridos"
                ));
            }

            DTOAuthResponse authResponse = servicioAutenticacion.autenticar(dtoLogin);
            return ResponseEntity.ok(authResponse);

        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new DTOErrorAuth(
                401,
                "Credenciales inválidas",
                e.getMessage()
            ));
        }
    }

    /**
     * POST /api/auth/registro
     * Registra un nuevo usuario en el sistema
     */
    @PostMapping("/registro")
    public ResponseEntity<?> registro(@RequestBody RegistroUsuarioDto registroDto) {
        try {
            if (!StringUtils.hasText(registroDto.getNombre()) ||
                !StringUtils.hasText(registroDto.getEmail()) ||
                !StringUtils.hasText(registroDto.getPassword())) {
                
                return ResponseEntity.badRequest().body(new DTOErrorAuth(
                    400,
                    "Datos incompletos",
                    "Nombre, email y contraseña son requeridos"
                ));
            }

            Usuario usuarioCreado = servicioRegistroUsuario.registrarUsuario(registroDto);
            
            DTOAuthResponse respuesta = new DTOAuthResponse();
            respuesta.setEmail(usuarioCreado.getEmail());
            respuesta.setIdUsuario(usuarioCreado.getIdUsuario());
            respuesta.setNombreUsuario(usuarioCreado.getNombre() + " " + usuarioCreado.getApellidos());
            respuesta.setMensaje("Registro exitoso - Por favor inicia sesión");
            
            return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new DTOErrorAuth(
                400,
                "Datos inválidos",
                e.getMessage()
            ));
        }
    }

    /**
     * POST /api/auth/validar-token
     * Valida si un token JWT es válido
     */
    @PostMapping("/validar-token")
    public ResponseEntity<?> validarToken(@RequestBody Map<String, String> body) {
        String token = body.get("token");
        
        if (!StringUtils.hasText(token)) {
            return ResponseEntity.ok(Map.of("valido", false));
        }

        boolean esValido = jwtTokenProvider.validarToken(token);
        return ResponseEntity.ok(Map.of("valido", esValido));
    }

    private UsuarioPerfilDTO mapearPerfil(Usuario u) {
        return new UsuarioPerfilDTO(
                u.getIdUsuario(),
                u.getNombre(),
                u.getApellidos(),
                u.getEmail(),
                u.getTelefono(),
                u.getRol(),
                u.getEstado(),
                u.getFechaRegistro()
        );
    }

    /**
     * GET /api/auth/info (PROTEGIDO)
     * Obtiene información del usuario autenticado
     */
    @GetMapping("/info")
public ResponseEntity<?> obtenerInfoUsuario() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    
    if (authentication == null || !authentication.isAuthenticated()) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
            new DTOErrorAuth(401, "No autorizado", "No hay sesión activa")
        );
    }

    String email = authentication.getName();

    Usuario usuario = servicioRegistroUsuario.obtenerPorEmail(email)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

    return ResponseEntity.ok(mapearPerfil(usuario));
}


    /**
     * PUT /api/auth/info
     * Actualiza datos del usuario autenticado (nombre, apellidos, telefono, contraseña opcional)
     */
    @PutMapping("/info")
public ResponseEntity<?> actualizarInfoUsuario(@RequestBody UsuarioUpdateDto dto) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    
    if (authentication == null || !authentication.isAuthenticated()) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                new DTOErrorAuth(401, "No autorizado", "No hay sesión activa")
        );
    }

    String email = authentication.getName();
    Usuario usuario = servicioRegistroUsuario.obtenerPorEmail(email)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

    // Actualizar datos
    if (dto.getNombre() != null) usuario.setNombre(dto.getNombre());
    if (dto.getApellidos() != null) usuario.setApellidos(dto.getApellidos());
    if (dto.getTelefono() != null) usuario.setTelefono(dto.getTelefono());
    if (dto.getContraseña() != null && !dto.getContraseña().isBlank()) {
        usuario.setContraseña(passwordEncoder.encode(dto.getContraseña()));
    }

    Usuario actualizado = servicioRegistroUsuario.actualizarUsuario(usuario);

    return ResponseEntity.ok(mapearPerfil(actualizado));
}


    /**
     * POST /api/auth/logout
     * Cierra la sesión del usuario (elimina el token del cliente)
     * Con JWT stateless, el backend no mantiene sesión, solo el cliente elimina el token
     */
    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        try {
            // Con JWT stateless, simplemente confirmamos que el logout fue exitoso
            // El cliente eliminará el token del localStorage/sessionStorage
            SecurityContextHolder.clearContext();
            
            return ResponseEntity.ok(Map.of(
                "mensaje", "Sesión cerrada exitosamente",
                "exitoso", true
            ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new DTOErrorAuth(
                500,
                "Error al cerrar sesión",
                e.getMessage()
            ));
        }
    }
}
