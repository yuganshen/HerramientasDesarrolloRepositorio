package com.herramientas.desarrollo.Service;

import com.herramientas.desarrollo.Entidades.Rol;
import com.herramientas.desarrollo.Repositorios.RolRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Inicializador de datos del sistema.
 * 
 * Se ejecuta automáticamente al iniciar la aplicación.
 * Crea los roles iniciales si no existen.
 */
@Component
public class InicializadorDatos implements CommandLineRunner {

    @Autowired
    private RolRepositorio rolRepositorio;

    /**
     * Método que se ejecuta al iniciar la aplicación.
     * 
     * Crea automáticamente los roles del sistema si no existen.
     * 
     * @param args Argumentos de línea de comandos
     * @throws Exception Si ocurre un error
     */
    @Override
    public void run(String... args) throws Exception {
        // Verificar si ya existe el rol CLIENTE
        if (!rolRepositorio.findByNombre("ROLE_CLIENTE").isPresent()) {
            Rol rolCliente = new Rol(
                "ROLE_CLIENTE",
                "Rol para usuarios clientes que compran productos",
                true
            );
            rolRepositorio.save(rolCliente);
            System.out.println("✓ Rol ROLE_CLIENTE creado exitosamente");
        } else {
            System.out.println("✓ Rol ROLE_CLIENTE ya existe");
        }

        // Verificar si ya existe el rol ADMINISTRADOR
        if (!rolRepositorio.findByNombre("ROLE_ADMINISTRADOR").isPresent()) {
            Rol rolAdmin = new Rol(
                "ROLE_ADMINISTRADOR",
                "Rol para administradores del sistema",
                true
            );
            rolRepositorio.save(rolAdmin);
            System.out.println("✓ Rol ROLE_ADMINISTRADOR creado exitosamente");
        } else {
            System.out.println("✓ Rol ROLE_ADMINISTRADOR ya existe");
        }

        System.out.println("================================");
        System.out.println("Inicialización de roles completada");
        System.out.println("================================");
    }
}
