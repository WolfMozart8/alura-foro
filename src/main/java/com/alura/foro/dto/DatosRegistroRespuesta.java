package com.alura.foro.dto;

import com.alura.foro.modelo.Topico;
import com.alura.foro.modelo.Usuario;

import java.time.LocalDateTime;

public record DatosRegistroRespuesta(
        Long topico_id,
        Long autor_id,
        String mensaje
        ) {
}
