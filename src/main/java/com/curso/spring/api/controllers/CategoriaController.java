package com.curso.spring.api.controllers;

import java.util.List;
import java.util.Optional;

import com.curso.spring.api.models.Categoria;
import com.curso.spring.api.repositories.CategoriaRepository;
import com.curso.spring.api.response.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoriaController {

    @Autowired //para inyectar dependencia 
    private CategoriaRepository categoriaRepository;
    
    @GetMapping("/saludar/{nombre}")
    public String saludar(@PathVariable String nombre)
    {
        return "HOLA "+ nombre;
    }

    @GetMapping("categorias/{id}")
    public ResponseEntity<Response<Categoria>> getCategoriaPorID(@PathVariable int id)
    {
        Response<Categoria> response=new Response<>();
        Optional categoria= categoriaRepository.findById(id);

        if(!categoria.isPresent())
        {
            response.getErrores().add("duplica");
            response.getErrores().add("incorrecto");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        response.setResultado((Categoria)categoria.get());
        return ResponseEntity.ok(response);

        /*Categoria categoria= categoriaRepository.findById(id).get();
        Response<Categoria> response=new Response<>();
        response.setResultado(categoria);
        response.getErrores().add("duplica");
        response.getErrores().add("incorrecta");
        //return ResponseEntity.status(HttpStatus.OK).body(categoria);
        return ResponseEntity.ok(response);*/
    }
    @GetMapping("/categorias")
    public List<Categoria> getCategoria()
    {
        List<Categoria> categorias= categoriaRepository.findAll();
        return categorias;
    }

    @PostMapping("categorias")
    public Categoria postCategoria(@RequestBody Categoria categoria) 
    {
        return categoriaRepository.save(categoria);  
    }
}