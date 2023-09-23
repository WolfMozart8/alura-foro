package com.alura.foro.dto;

import com.alura.foro.modelo.Curso;
import com.alura.foro.modelo.StatusTopico;
import com.alura.foro.modelo.Topico;
import com.alura.foro.modelo.Usuario;

import java.time.LocalDateTime;

public record DatosObtenerTopicos(
        String titulo,
        String mensaje,
        LocalDateTime fecha,
        StatusTopico statusTopico,
        String autor,
        String curso
) {
    public DatosObtenerTopicos(Topico topico) {
        this(
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaCreacion(),
                topico.getStatus(),
                topico.getAutor().getNombre(),
                topico.getCurso().getNombre()
        );
    }
}
