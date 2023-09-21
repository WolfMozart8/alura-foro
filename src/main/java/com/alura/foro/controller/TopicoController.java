package com.alura.foro.controller;

import com.alura.foro.dto.DatosRegistroTopico;
import com.alura.foro.modelo.Topico;
import com.alura.foro.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository repository;

    @PostMapping
    public ResponseEntity RegistrarTopico(@RequestBody DatosRegistroTopico datosRegistroTopico) {
        repository.save(new Topico(datosRegistroTopico));
        System.out.println(datosRegistroTopico);
        return ResponseEntity.ok().body(datosRegistroTopico);
    }
}
