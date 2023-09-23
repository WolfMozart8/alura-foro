package com.alura.foro.services;

import com.alura.foro.dto.DatosModificarTopico;
import com.alura.foro.dto.DatosObtenerTopicos;
import com.alura.foro.dto.DatosRegistroTopico;
import com.alura.foro.modelo.Topico;
import com.alura.foro.repository.CursoRepository;
import com.alura.foro.repository.RespuestaRepository;
import com.alura.foro.repository.TopicoRepository;
import com.alura.foro.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicoService {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private CursoRepository cursoRepository;
    @Autowired
    private RespuestaRepository respuestaRepository;


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

    public List<DatosObtenerTopicos> obtenerTopicos() {
        List<Topico> topicos = topicoRepository.findAll();

        return topicos.stream().map(topico -> new DatosObtenerTopicos(topico)).toList();
    }

    public DatosObtenerTopicos obtenerTopico (Long id) {
        Topico topico = topicoRepository.findById(id).get();

        return new DatosObtenerTopicos(topico);
    }

    public DatosModificarTopico modificarTopico(DatosModificarTopico datosModificarTopico){
        var topico = topicoRepository.getReferenceById(datosModificarTopico.id());
        topico.modificar(datosModificarTopico);
        System.out.println(topico);
        return new DatosModificarTopico(topico);
    }

    public void eliminarTopico (Long id) {
//        topicoRepository.deleteById(id);
        var topico = topicoRepository.findById(id);

        if (topico.isPresent()) {
            Topico topico1 = topico.get();
            topicoRepository.delete(topico1);
        } else {
            throw new EntityNotFoundException(String.format("el topico con el id %d no fue encontrado", id));
        }
    }
}
