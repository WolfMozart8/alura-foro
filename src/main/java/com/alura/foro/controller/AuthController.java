package com.alura.foro.controller;

import com.alura.foro.dto.DatosAutenticarUsuario;
import com.alura.foro.dto.DatosTokenJWT;
import com.alura.foro.infra.security.TokenService;
import com.alura.foro.modelo.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthController {

    @Autowired
    private TokenService tokenService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping
    public ResponseEntity autenticarUsuario(@RequestBody DatosAutenticarUsuario datos) {
        Authentication authToken = new UsernamePasswordAuthenticationToken(datos.nombre(), datos.contrasena());

        var usuario = authenticationManager.authenticate(authToken);
        var JWTtoken = tokenService.generarToken((Usuario) usuario.getPrincipal());
        return ResponseEntity.ok(new DatosTokenJWT(JWTtoken));

    }
}
