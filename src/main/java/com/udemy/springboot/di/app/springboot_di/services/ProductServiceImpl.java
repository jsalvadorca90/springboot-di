package com.udemy.springboot.di.app.springboot_di.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.udemy.springboot.di.app.springboot_di.models.Product;
import com.udemy.springboot.di.app.springboot_di.repositories.ProductRepository;

// Esta anotación de Spring Framework indica que la clase ProductServiceImpl es un servicio.En Spring,un servicio es una clase que contiene la lógica de negocio de la aplicación. La capa de servicio se encuentra entre la capa de controlador (que maneja las peticiones HTTP) y la capa de repositorio (que accede a la base de datos).
@Service
// Implementación de la Interfaz ProductService
public class ProductServiceImpl implements ProductService {

    // // inyectar dependencia mediante atributo
    // @Autowired
    // private Environment environment;
    @Value("${config.tax.price}")
    private Double tax;
    // // anotación que permite inyectar objeto spring[bean/componente] obligatorio en atributo
    // @Autowired
    // // si la inyección de dependencia es por atributo, el @Qualifier va después del @Autowired
    // @Qualifier("productFoo")
    // Para el caso de @Primary: Si declaras 'ProductRepository productRepositoryFoo', como el nombre "productRepositoryFoo" es el mismo que el de la clase 'ProductRepositoryFoo' en camelCase, la elige por defecto sin usar @Primary, es como si tuviera el @Primary implicitamente
    private ProductRepository repository;

    // // necesario en método 'set'
    // @Autowired
    // public void setRepository(ProductRepository repository) {
    //     this.repository = repository;
    // }

    // inyectar dependencia mediante constructor requiere "private Environment environment"
    // No es necesario colocar @Autowired cuando se trata de DI mediante constructor
    // // @Qualifier le dice a Spring que inyecte el bean con el nombre "productRepositoryImpl", que corresponde a la clase "ProductRepositoryImpl"
    // public ProductServiceImpl(@Qualifier("productList") ProductRepository repository, Environment environment) {
    // // sin Environment
    // public ProductServiceImpl(@Qualifier("productList") ProductRepository repository) {
    // @Qualifier referencia al método de la clase AppConfig con el nombre persnalizado "productJson"
    public ProductServiceImpl(@Qualifier("productJson") ProductRepository repository) {
        this.repository = repository;
        // this.environment = environment;
    }

    @Override
    public List<Product> findAll() {
        // findAll(): Se encarga de obtener todos los productos de una base de datos o de cualquier otra fuente de datos. .stream(): Convierte el resultado de repository.findAll() (que es una lista de productos) en un flujo (stream).map(p -> { ... }): Este método map se aplica a cada elemento del flujo (cada producto). La parte p -> { ... } es una expresión lambda que define una función que se aplicará a cada producto.
        return repository.findAll().stream().map(p -> {
            // // muestra el valor de la propiedad "config.tax.price"
            // System.out.println(environment.getProperty("config.tax.price", Double.class));
            // // Para cada producto p, se calcula un nuevo precio (priceTax) incrementando el precio original en un 25%.
            // // trabaja con la propiedad "config.tax.price" del archivo "config.properties"
            // Double priceTax = p.getPrice() * environment.getProperty("config.tax.price", Double.class);
            // // muestra el valor de la variable "tax"
            // System.out.println(tax);
            // trabaja con la variable "tax"
            Double priceTax = p.getPrice() * tax;
            // Romper PRINCIPIO DE INMUTABILIDAD
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
            // // MUTABILIDAD de objeto 
            // p.setPrice(priceTax.longValue()); // se modifica el precio del producto clonado, no el original
            // return p;

            // Finalmente, todos los productos modificados se recolectan en una nueva lista y se devuelve como resultado del método findAll()
        }).collect(Collectors.toList());
    }

    @Override
    public Product findById(Long id) {
        return repository.findById(id);
    }

}
