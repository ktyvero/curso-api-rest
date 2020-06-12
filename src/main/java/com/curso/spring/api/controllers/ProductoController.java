package com.curso.spring.api.controllers;
import java.util.List;
import java.util.Optional;

import com.curso.spring.api.models.Producto;
import com.curso.spring.api.repositories.ProductoRepository;
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
public class ProductoController {
    @Autowired
    private ProductoRepository productoRepository;

    @GetMapping("productos/{id}")
    public ResponseEntity<Response<Producto>> getProductoPorID(@PathVariable int id)
    {
        Response<Producto> response=new Response<>();
        Optional producto= productoRepository.findById(id);

        if(!producto.isPresent())
        {
            response.getErrores().add("No existe el producto");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        response.setResultado((Producto)producto.get());
        return ResponseEntity.ok(response);      
    }

    @GetMapping("/productos")
    public List<Producto> getProducto()
    {
        List<Producto> productos= productoRepository.findAll();
        return productos;
    }

    @PostMapping("productos")
    public Producto postProducto(@RequestBody Producto producto) 
    {
        return productoRepository.save(producto);  
    }
    
}