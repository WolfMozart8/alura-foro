package com.alura.foro.dto;

public record DatosRegistroTopico(
        String titulo,
        String mensaje,
        //TODO: Cambiar a tipos reales
        String autor,
        String curso
) {
}
