package com.herramientas.desarrollo.Repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.herramientas.desarrollo.Entidades.Usuario;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {

}
