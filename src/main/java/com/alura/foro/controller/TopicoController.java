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

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoService topicoService;


    @PostMapping
    @Transactional
    public ResponseEntity<Topico> RegistrarTopico(@RequestBody @Valid DatosRegistroTopico datosRegistroTopico) {
        System.out.println("1: " +  datosRegistroTopico);

        Topico topicoCreado = topicoService.RegistrarTopico(datosRegistroTopico);

        //TODO: mejorar respuesta
        if (topicoCreado.getAutor() == null || topicoCreado.getCurso() == null){
            System.out.println("en badBAD");
            return ResponseEntity.badRequest().body(topicoCreado);
        }

        System.out.println("2: " +  datosRegistroTopico);
        return ResponseEntity.status(HttpStatus.CREATED).body(topicoCreado);
    }
}
