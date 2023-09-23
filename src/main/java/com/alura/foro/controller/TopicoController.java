package com.alura.foro.controller;

import com.alura.foro.dto.DatosRegistroTopico;
import com.alura.foro.modelo.Topico;
import com.alura.foro.services.TopicoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoService topicoService;


    @PostMapping
    @Transactional
    public ResponseEntity<Topico> RegistrarTopico(@RequestBody @Valid DatosRegistroTopico datosRegistroTopico, UriComponentsBuilder uriComponentsBuilder) {
        Topico topicoCreado = topicoService.RegistrarTopico(datosRegistroTopico);

        // devuelve en el header el link directo al topico creado (/topicos/id)
        URI uri = uriComponentsBuilder.path("topicos/{id}").buildAndExpand(topicoCreado.getId()).toUri();
        return ResponseEntity.created(uri).body(topicoCreado);
    }

    @GetMapping
    public ResponseEntity obtenerTopicos () {
        var topicos = topicoService.obtenerTopicos();

        return ResponseEntity.ok().body(topicos);
    }

    @GetMapping("/{id}")
    public ResponseEntity obtenerTopicoPorId(@PathVariable Long id) {
        var topico = topicoService.obtenerTopico(id);

        return ResponseEntity.ok().body(topico);
    }
}
