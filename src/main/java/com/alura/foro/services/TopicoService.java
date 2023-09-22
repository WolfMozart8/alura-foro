package com.alura.foro.services;

import com.alura.foro.dto.DatosRegistroTopico;
import com.alura.foro.modelo.Topico;
import com.alura.foro.repository.CursoRepository;
import com.alura.foro.repository.TopicoRepository;
import com.alura.foro.repository.UsuarioRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TopicoService {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private CursoRepository cursoRepository;


    public Topico RegistrarTopico(DatosRegistroTopico datosRegistroTopico) {

        if (topicoRepository.existsByTituloOrMensaje(datosRegistroTopico.titulo(), datosRegistroTopico.mensaje())){
            throw new ValidationException("no duplicados");
        }
        //TODO: mejorar verificacion de usuario y curso
        var usuario = usuarioRepository.findById(datosRegistroTopico.autor_id()).get();
        var curso = cursoRepository.findById(datosRegistroTopico.curso_id()).get();

        Topico topico = new Topico(datosRegistroTopico);
        topico.setAutor(usuario);
        topico.setCurso(curso);
        return topicoRepository.save(topico);
    }
}
