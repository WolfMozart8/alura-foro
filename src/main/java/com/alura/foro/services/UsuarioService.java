package com.alura.foro.services;

import com.alura.foro.dto.DatosRegistroUsuario;
import com.alura.foro.modelo.Usuario;
import com.alura.foro.repository.RespuestaRepository;
import com.alura.foro.repository.TopicoRepository;
import com.alura.foro.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Arrays;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private TopicoRepository topicoRepository;
    @Autowired
    private RespuestaRepository respuestaRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public Usuario RegistrarUsuario(@RequestBody @Valid DatosRegistroUsuario datosRegistroUsuario) {
        Usuario usuario = new Usuario(datosRegistroUsuario);
        usuario.setContrasena(passwordEncoder.encode(datosRegistroUsuario.contrasena()));
        return usuarioRepository.save(usuario);
    }

    public Usuario obtenerDatosUsuario(Long id) {
        var existeUsuario = usuarioRepository.existsById(id);
            var usuario = usuarioRepository.findById(id).get();
            var obtenerTopicos = Arrays.stream(topicoRepository.findByAutorId(id)).toList();
            var obtenerRespuestas = Arrays.stream(respuestaRepository.findByAutorId(id)).toList();

            usuario.setTopicos(obtenerTopicos);
            usuario.setRespuestas(obtenerRespuestas);

            return usuario;

    }
}
