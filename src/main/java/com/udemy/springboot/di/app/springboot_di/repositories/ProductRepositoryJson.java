package com.udemy.springboot.di.app.springboot_di.repositories;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.udemy.springboot.di.app.springboot_di.models.Product;

public class ProductRepositoryJson implements ProductRepository {

    private List<Product> list;
    
    // constructor sin atributo
    // forma programática con el ClassPathResource
    public ProductRepositoryJson() {
        ClassPathResource resource = new ClassPathResource("json/product.json");
        readValueJson(resource);
    }
    
    // constructor con atributo
    // forma declarativa sin el ClassPathResource
    public ProductRepositoryJson(Resource resource) {
        // ClassPathResource resource = new ClassPathResource("json/product.json");
        readValueJson(resource);
    }
    
    // Método para leer el JSON y transformarlo a una lista de Productos.
    private void readValueJson (Resource resource) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            // Leemos el archivo JSON y lo transformamos a una lista de Productos
            // // resource.getFile(): Obtiene el objeto de archivo del ClassPathResource
            // objectMapper.readValue(...): Lee los datos JSON del archivo y los mapea a un array de objetos Product (Product[].class)
            // Arrays.asList(...): Convierte el array de Product en una List<Product>
            // list = Arrays.asList(objectMapper.readValue(resource.getFile(), Product[].class));
            // El método getInputStream() de ClassPathResource devuelve un InputStream que permite leer el contenido del archivo
            list = Arrays.asList(objectMapper.readValue(resource.getInputStream(), Product[].class));
        // en los bloques catch: Si ocurre alguna de las excepciones, esto imprimirá el seguimiento de la pila en la consola, lo cual es útil para la depuración
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Product> findAll() {
        return list;
    }

    @Override
    public Product findById(Long id) {
        // convierte la lista en un flujo, luego filtra el flujo para encontrar el producto cuyo ID coincida con el ID proporcionado. Intenta encontrar el primer producto que coincida. Si se encuentra un producto, se devuelve. Si no se encuentra ningún producto con el ID especificado, se lanza una excepción.
        return list.stream().filter(p -> p.getId().equals(id)).findFirst().orElseThrow();
    }

}
