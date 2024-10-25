package com.udemy.springboot.di.app.springboot_di.repositories;

import java.util.Arrays;
import java.util.List;

import com.udemy.springboot.di.app.springboot_di.models.Product;

public class ProductRepositoryImpl implements ProductRepository {
    
    private List<Product> data;

    public ProductRepositoryImpl() {
        this.data = Arrays.asList(
                new Product(1L, "Memoria de consair 32", 300L),
                new Product(2L, "CPU Intel Core i9", 850L),
                new Product(3L, "Teclado Razer Mini 60%", 180L),
                new Product(4L, "Motherboard Gigabyte", 490L));
    }
    
    @Override
    public List<Product> findAll() {
        return data;
    }

    @Override
    public Product findById(Long id) {
        // convierte esa colección en un flujo (stream) de objetos Product, lo cual nos permite aplicar operaciones en cadena para filtrar y buscar elementos
        return data.stream()
                // filtra el flujo de productos. Para cada producto p en el flujo, se verifica si su ID (p.getId()) es igual al ID proporcionado en el parámetro del método (id). Solo los productos que cumplan esta condición pasarán al siguiente paso
                .filter(p -> p.getId().equals(id))
                // intenta encontrar el primer producto del flujo que cumpla con la condición del filtro. Si encuentra un producto, lo devuelve
                .findFirst()
                // Si no se encuentra ningún producto que coincida con el ID proporcionado, esta parte devuelve null. Esto indica que no se encontró un producto con ese ID
                .orElse(null);
    }

}
