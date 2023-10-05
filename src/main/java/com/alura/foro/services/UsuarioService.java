package com.alura.foro.services;

import com.alura.foro.dto.DatosRegistroUsuario;
import com.alura.foro.modelo.Usuario;
import com.alura.foro.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public Usuario RegistrarUsuario(@RequestBody @Valid DatosRegistroUsuario datosRegistroUsuario) {
        Usuario usuario = new Usuario(datosRegistroUsuario);
        usuario.setContrasena(passwordEncoder.encode(datosRegistroUsuario.contrasena()));
        return repository.save(usuario);
    }
}
