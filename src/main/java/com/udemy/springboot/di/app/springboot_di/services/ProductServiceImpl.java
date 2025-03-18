package com.udemy.springboot.di.app.springboot_di.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udemy.springboot.di.app.springboot_di.models.Product;
import com.udemy.springboot.di.app.springboot_di.repositories.ProductRepository;

// Esta anotación de Spring Framework indica que la clase ProductServiceImpl es un servicio.En Spring,un servicio es una clase que contiene la lógica de negocio de la aplicación. La capa de servicio se encuentra entre la capa de controlador (que maneja las peticiones HTTP) y la capa de repositorio (que accede a la base de datos).
@Service
// Implementación de la Interfaz ProductService
public class ProductServiceImpl implements ProductService {

    // anotación que permite inyectar objeto spring[bean/componente]
    @Autowired
    private ProductRepository repository;

    @Override
    public List<Product> findAll() {
        // findAll(): Se encarga de obtener todos los productos de una base de datos o de cualquier otra fuente de datos. .stream(): Convierte el resultado de repository.findAll() (que es una lista de productos) en un flujo (stream). .map(p -> { ... }): Este método map se aplica a cada elemento del flujo (cada producto). La parte p -> { ... } es una expresión lambda que define una función que se aplicará a cada producto.
        return repository.findAll().stream().map(p -> {
            // Para cada producto p, se calcula un nuevo precio (priceTax) incrementando el precio original en un 25%.
            Double priceTax = p.getPrice() * 1.25d;
            // rompre PRINCIPIO DE INMUTABILIDAD
            // // Se asigna el nuevo precio (convertido a un número entero largo) al atributo price del producto actual
            // p.setPrice(priceTax.longValue());
            // // Se devuelve el producto modificado con el nuevo precio
            // return p;
            // // solución: PRINCIPIO DE INMUTABILIDAD
            // Product newProd = new Product(p.getId(), p.getName(), priceTax.longValue());
            // solución alterna: PRINCIPIO DE INMUTABILIDAD
            Product newProd = (Product) p.clone();
            newProd.setPrice(priceTax.longValue()); // se modifica el precio del producto clonado, no el original
            return newProd;

            // Finalmente, todos los productos modificados se recolectan en una nueva lista y se devuelve como resultado del método findAll()
        }).collect(Collectors.toList());
    }

    @Override
    public Product findById(Long id) {
        return repository.findById(id);
    }

}
