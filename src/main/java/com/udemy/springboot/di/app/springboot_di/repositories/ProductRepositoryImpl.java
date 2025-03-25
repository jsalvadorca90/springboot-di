package com.udemy.springboot.di.app.springboot_di.repositories;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.udemy.springboot.di.app.springboot_di.models.Product;

// se utiliza para indicar que un bean debe tener prioridad cuando se inyecta una dependencia y existen múltiples beans candidatos
@Primary
// // El scope pro defecto es 'singleton', el scope 'request' se utiliza para definir el alcance (scope) de un bean a nivel de petición HTTP. Esto significa que el contenedor de Spring creará una nueva instancia del bean cada vez que se reciba una nueva petición HTTP y esa instancia estará disponible solo durante el ciclo de vida de esa petición. Es especialmente útil en aplicaciones web donde la información de cada petición debe manejarse de forma independiente.
// @RequestScope
// // se utiliza para definir el alcance (scope) de un bean a nivel de sesión HTTP. Esto significa que el contenedor de Spring creará una nueva instancia del bean para cada sesión HTTP activa y esa instancia estará disponible durante toda la vida de esa sesión. Es una herramienta fundamental para construir aplicaciones web con estado de usuario.
// @SessionScope
// Esta anotación de Spring Framework indica que la clase ProductRepositoryImpl es un repositorio.En Spring,un repositorio es una clase que se encarga de acceder a la base de datos o a cualquier otra fuente de datos. Esta anotación es parte del módulo Spring Data y es especialmente útil cuando se trabaja con Spring Data JPA o Spring Data MongoDB, ya que facilita la creación de repositorios para interactuar con bases de datos.
// "productList" especifica el nombre del bean para inyectar la dependencia del repositorio en otras clases utilizando la anotación @Autowired junto con @Qualifier("productList")
@Repository("productList")
// Implementación de la Interfaz ProductRepository
public class ProductRepositoryImpl implements ProductRepository {
    
    private List<Product> data;

    // Constructor inicializador que carga los productos de prueba
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
