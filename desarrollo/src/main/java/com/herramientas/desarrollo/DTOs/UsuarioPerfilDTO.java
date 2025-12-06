package com.herramientas.desarrollo.DTOs;

import java.time.LocalDateTime;

public record UsuarioPerfilDTO(
        Long idUsuario,
        String nombre,
        String apellidos,
        String email,
        String telefono,
        String rol,
        String estado,
        LocalDateTime fechaRegistro
) {}