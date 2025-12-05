package com.herramientas.desarrollo.Seguridad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

/**
 * Configuración principal de Spring Security.
 * 
 * Define:
 * - Autenticación (cómo validar credenciales)
 * - Autorización (quién puede acceder a qué)
 * - Filtros de seguridad (JWT, CORS, etc)
 * - Configuración de sesiones (stateless con JWT)
 * 
 * Está lista para integración con Angular en localhost:4200
 */
@Configuration
@EnableWebSecurity
public class ConfiguracionSeguridad {

    @Autowired
    private ServicioDetallesUsuario servicioDetallesUsuario;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    /**
     * Define el codificador de contraseñas a usar.
     * BCrypt genera un hash único por contraseña, imposible de revertir.
     *
     * @return BCryptPasswordEncoder configurado
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        // Usar BCrypt con factor de fortaleza 10 (predeterminado)
        return new BCryptPasswordEncoder();
    }

    /**
     * Construye el AuthenticationManager que se encarga de validar credenciales.
     *
     * @param http HttpSecurity para configurar
     * @return AuthenticationManager listo para usar
     * @throws Exception Si ocurre error en la configuración
     */
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = 
            http.getSharedObject(AuthenticationManagerBuilder.class);
        
        authenticationManagerBuilder
                // Usar nuestro servicio de detalles de usuario
                .userDetailsService(servicioDetallesUsuario)
                // Codificar contraseñas con BCrypt
                .passwordEncoder(passwordEncoder());
        
        return authenticationManagerBuilder.build();
    }

    /**
     * Configuración de CORS (Cross-Origin Resource Sharing).
     * Permite que Angular en localhost:4200 haga solicitudes a esta API.
     *
     * @return Configuración CORS
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        
        // Orígenes permitidos (Angular en desarrollo)
        configuration.setAllowedOrigins(Arrays.asList(
            "http://localhost:4200",
            "http://127.0.0.1:4200",
            "http://localhost:3000"
        ));
        
        // Métodos HTTP permitidos
        configuration.setAllowedMethods(Arrays.asList(
            "GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH"
        ));
        
        // Headers permitidos (importante incluir Authorization)
        configuration.setAllowedHeaders(Arrays.asList("*"));
        
        // Permitir enviar cookies/credenciales
        configuration.setAllowCredentials(true);
        
        // Tiempo que el navegador cachea la configuración CORS (1 hora)
        configuration.setMaxAge(3600L);
        
        // Aplicar a todas las rutas
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        
        return source;
    }

    /**
     * Cadena de filtros de seguridad.
     * Define qué rutas son públicas y cuáles requieren autenticación.
     *
     * @param http HttpSecurity para configurar
     * @return SecurityFilterChain configurada
     * @throws Exception Si ocurre error
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // Deshabilitar CSRF (no necesario con JWT y sin sesiones)
            .csrf(csrf -> csrf.disable())
            
            // Habilitar CORS desde la configuración arriba
            .cors(cors -> cors.configurationSource(corsConfigurationSource()))
            
            // Sesiones STATELESS (no usar cookies de sesión)
            .sessionManagement(session -> 
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )
            
            // Configurar autorización de rutas
            .authorizeHttpRequests(auth -> auth
                // Rutas públicas - Sin autenticación requerida
                .requestMatchers(HttpMethod.POST, "/api/auth/login").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/auth/registro").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/auth/validar-token").permitAll()
                
                //rutas de apis del Frond
                .requestMatchers(HttpMethod.GET, "/apiproducto/**").permitAll()
                
                //----------------------------
  
                //----------------------------
                // *** PERMITIR ACCESO A IMÁGENES ***
                .requestMatchers("/img/**").permitAll()
                
                // Rutas públicas para productos y categorías (lectura)
                .requestMatchers(HttpMethod.GET, "/api/productos/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/categorias/**").permitAll()
                
                // Rutas protegidas - Requieren autenticación
                .requestMatchers(HttpMethod.GET, "/api/auth/info").authenticated()
                
                // Rutas de cliente - Requieren rol ROLE_CLIENTE o ROLE_ADMINISTRADOR
                .requestMatchers("/api/carrito/**").hasAnyRole("CLIENTE", "ADMINISTRADOR")
                .requestMatchers("/api/pedidos/**").hasAnyRole("CLIENTE", "ADMINISTRADOR")
                .requestMatchers("/api/perfil/**").hasAnyRole("CLIENTE", "ADMINISTRADOR")
                
                // Rutas de administrador - Solo ROLE_ADMINISTRADOR
                .requestMatchers("/api/admin/**").hasRole("ADMINISTRADOR")
                .requestMatchers(HttpMethod.POST, "/api/productos/**").hasRole("ADMINISTRADOR")
                .requestMatchers(HttpMethod.PUT, "/api/productos/**").hasRole("ADMINISTRADOR")
                .requestMatchers(HttpMethod.DELETE, "/api/productos/**").hasRole("ADMINISTRADOR")
                
                // Todas las demás rutas requieren autenticación
                .anyRequest().authenticated()
            )
            
            // Manejo de excepciones de seguridad
            .exceptionHandling(exception ->
                exception
                    // Cuando no hay autenticación (token faltante o inválido)
                    .authenticationEntryPoint((request, response, authException) -> {
                        response.setStatus(401);
                        response.setContentType("application/json");
                        response.getWriter().write("{\"error\": \"No autorizado - Token faltante o inválido\"}");
                    })
                    // Cuando hay autenticación pero sin permisos (rol insuficiente)
                    .accessDeniedHandler((request, response, accessDeniedException) -> {
                        response.setStatus(403);
                        response.setContentType("application/json");
                        response.getWriter().write("{\"error\": \"Acceso denegado - Rol insuficiente\"}");
                    })
            );
        
        // Agregar filtro JWT ANTES del filtro de autenticación estándar
        http.addFilterBefore(new FiltroJwt(), UsernamePasswordAuthenticationFilter.class);
        
        return http.build();
    }
}
