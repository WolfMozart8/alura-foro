package com.alura.foro.dto;

import com.alura.foro.modelo.Usuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.util.List;

public record DatosObtenerUsuario(
        Long id,
        String nombre,
        List<DatosObtenerTopico> topicos,
        List<DatosObtenerRespuesta> respuestas
) {
    public DatosObtenerUsuario(Usuario usuario) {
        this(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getTopicos().stream().map(DatosObtenerTopico::new).toList(),
                usuario.getRespuestas().stream().map(DatosObtenerRespuesta::new).toList()
        );
    }
}
