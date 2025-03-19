package com.udemy.springboot.di.app.springboot_di.repositories;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.udemy.springboot.di.app.springboot_di.models.Product;

// se utiliza para indicar que una clase debe tener prioridad sobre otras clases que 'inyectan la misma dependencia'
// @Primary
@Repository("productFoo")
public class ProductRepositoryFoo implements ProductRepository {

    @Override
    public List<Product> findAll() {
        return Collections.singletonList(new Product(1L, "Monitor ASUS 27", 600L));
    }

    @Override
    public Product findById(Long id) {
        return new Product(id, "Monitor ASUS 27", 600L);
    }

}
