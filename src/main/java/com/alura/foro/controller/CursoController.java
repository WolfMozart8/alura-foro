package com.alura.foro.controller;

import com.alura.foro.dto.DatosRegistroCurso;
import com.alura.foro.modelo.Curso;
import com.alura.foro.repository.CursoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    @Autowired
    private CursoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity registrarCurso(@RequestBody DatosRegistroCurso datosRegistroCurso) {
        System.out.println(datosRegistroCurso);
        Curso curso = new Curso(datosRegistroCurso);
        repository.save(curso);
        return ResponseEntity.ok().body(datosRegistroCurso);
    }
}
