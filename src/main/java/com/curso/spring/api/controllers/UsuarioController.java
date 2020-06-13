package com.curso.spring.api.controllers;

import com.curso.spring.api.models.Usuario;
import com.curso.spring.api.repositories.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsuarioController {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired BCryptPasswordEncoder bcryp;

    @PostMapping("/registro")
    public ResponseEntity<?> registrar(@RequestBody Usuario usuario)
    {
        usuario.setClave(bcryp.encode(usuario.getClave()));
        Usuario usuarioAlmacenado= usuarioRepository.save(usuario);
        return ResponseEntity.ok(usuarioAlmacenado);
    }
    
}