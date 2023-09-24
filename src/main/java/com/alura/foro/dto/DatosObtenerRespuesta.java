package com.alura.foro.dto;

import com.alura.foro.modelo.Respuesta;

public record DatosObtenerRespuesta(
        Long id,
        String autor,
        String mensaje
) {
    public DatosObtenerRespuesta(Respuesta respuesta){
        this(
                respuesta.getId(),
                respuesta.getAutor().getNombre(),
                respuesta.getMensaje()
        );
    }
}
