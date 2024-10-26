package com.udemy.springboot.di.app.springboot_di.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.udemy.springboot.di.app.springboot_di.models.Product;
import com.udemy.springboot.di.app.springboot_di.services.ProductService;


@RestController
@RequestMapping("/api")
public class SomeController {

    @Autowired
    private ProductService service;

    // busca todos los valores
    @GetMapping
    public List<Product> list() {
        return service.findAll();
    }

    // busca un valor por id
    @GetMapping("/{id}")
    public Product show(@PathVariable Long id) {
        return service.findById(id);
    }
    
}
