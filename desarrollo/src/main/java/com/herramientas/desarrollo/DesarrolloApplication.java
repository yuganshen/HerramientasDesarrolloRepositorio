package com.herramientas.desarrollo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.herramientas.desarrollo.Entidades.Producto;
import com.herramientas.desarrollo.Repositorios.ProductoRepositorio;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DesarrolloApplication {
    public static void main(String[] args) {
        SpringApplication.run(DesarrolloApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(ProductoRepositorio repo) {
        return (args) -> {
            repo.save(new Producto(null, "Teclado mecánico RGB", 150.0, 20));
            repo.save(new Producto(null, "Mouse gamer inalámbrico", 90.0, 15));
            repo.save(new Producto(null, "Audífonos HyperX Cloud", 220.0, 10));
        };
    }
}