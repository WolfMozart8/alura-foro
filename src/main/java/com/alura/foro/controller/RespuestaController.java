package com.alura.foro.controller;

import com.alura.foro.dto.DatosRegistroRespuesta;
import com.alura.foro.services.TopicoService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("topicos/respuestas")
public class RespuestaController {

    @Autowired
    private TopicoService topicoService;

    @PostMapping
    @Transactional
    public ResponseEntity crearRespuesta( @RequestBody DatosRegistroRespuesta datosRegistroRespuesta) {
        var respuesta = topicoService.agregarRespuesta(datosRegistroRespuesta);
        var topico = topicoService.obtenerTopico(datosRegistroRespuesta.topico_id());

        return ResponseEntity.ok().body(respuesta);
    }

}
