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
        };
    }
}