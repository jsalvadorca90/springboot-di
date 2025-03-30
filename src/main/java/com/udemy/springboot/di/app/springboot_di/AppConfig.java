package com.udemy.springboot.di.app.springboot_di;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;

import com.udemy.springboot.di.app.springboot_di.repositories.ProductRepository;
import com.udemy.springboot.di.app.springboot_di.repositories.ProductRepositoryJson;

@Configuration
@PropertySource("classpath:config.properties")
public class AppConfig {

    
    @Value("classpath:json/product.json")
    private Resource resource;

    // componente con nombre lógico personalizado para el uso del @Qualifier
    @Bean("productJson")
    // // se utiliza para indicar que un bean debe tener prioridad cuando se inyecta una dependencia y existen múltiples beans candidatos
    // @Primary
    ProductRepository productRepositoryJson() {
        return new ProductRepositoryJson(resource);
    }

}
