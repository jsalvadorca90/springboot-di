package com.udemy.springboot.di.app.springboot_di.services;

import java.util.List;
import java.util.stream.Collectors;

import com.udemy.springboot.di.app.springboot_di.models.Product;
import com.udemy.springboot.di.app.springboot_di.repositories.ProductRepository;

public class ProductService {

    private ProductRepository repository = new ProductRepository();

    public List<Product> findAll() {
        // findAll(): Se encarga de obtener todos los productos de una base de datos o de cualquier otra fuente de datos. .stream(): Convierte el resultado de repository.findAll() (que es una lista de productos) en un flujo (stream). .map(p -> { ... }): Este método map se aplica a cada elemento del flujo (cada producto). La parte p -> { ... } es una expresión lambda que define una función que se aplicará a cada producto.
        return repository.findAll().stream().map(p -> {
            // Para cada producto p, se calcula un nuevo precio (priceImp) incrementando el precio original en un 25%.
            Double priceImp = p.getPrice() * 1.25d;
            // Se asigna el nuevo precio (convertido a un número entero largo) al atributo price del producto actual
            p.setPrice(priceImp.longValue());
            // Se devuelve el producto modificado con el nuevo precio
            return p;
        // Finalmente, todos los productos modificados se recolectan en una nueva lista y se devuelve como resultado del método findAll()
        }).collect(Collectors.toList());
    }

    public Product findById(Long id) {
        return repository.findById(id);
    }

}
