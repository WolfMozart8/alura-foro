package com.alura.foro.controller;

import com.alura.foro.dto.DatosObtenerUsuario;
import com.alura.foro.dto.DatosRegistroUsuario;
import com.alura.foro.modelo.Usuario;
import com.alura.foro.repository.UsuarioRepository;
import com.alura.foro.services.UsuarioService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository repository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UsuarioService usuarioService;


    @PostMapping
    @Transactional
    public ResponseEntity RegistrarUsuario(@RequestBody @Valid DatosRegistroUsuario datosRegistroUsuario, UriComponentsBuilder uriComponentsBuilder) {
        Usuario usuarioRegistrado = usuarioService.RegistrarUsuario(datosRegistroUsuario);
        URI uri = uriComponentsBuilder.path("usuarios/{id}").buildAndExpand(usuarioRegistrado.getId()).toUri();
        return ResponseEntity.created(uri).body(datosRegistroUsuario);
    }

    @GetMapping("/{id}")
    public ResponseEntity obtenerDatosUsuario(@PathVariable Long id){
        Usuario usuario = usuarioService.obtenerDatosUsuario(id);
        DatosObtenerUsuario datos = new DatosObtenerUsuario(usuario);

        return ResponseEntity.ok().body(datos);
    }
}
