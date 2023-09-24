package com.alura.foro.services;

import com.alura.foro.dto.DatosRegistroRespuesta;
import com.alura.foro.modelo.Respuesta;
import com.alura.foro.repository.RespuestaRepository;
import com.alura.foro.repository.TopicoRepository;
import com.alura.foro.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RespuestaService {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RespuestaRepository respuestaRepository;

    public Respuesta crearRespuesta(DatosRegistroRespuesta datosRegistroRespuesta){
        var respuesta = new Respuesta(datosRegistroRespuesta);

        var autor = usuarioRepository.getReferenceById(datosRegistroRespuesta.autor_id());
        var topico = topicoRepository.getReferenceById(datosRegistroRespuesta.topico_id());

        respuesta.setAutor(autor);
        respuesta.setTopico(topico);

        return respuestaRepository.save(respuesta);
    }
}
