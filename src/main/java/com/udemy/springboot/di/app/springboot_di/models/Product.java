package com.udemy.springboot.di.app.springboot_di.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product implements Cloneable {
    
    private Long id;
    private String name;
    private Long price;

    // Esta anotación indica que el método clone() está sobrescribiendo (reemplazando) un método clone() heredado de la clase Ob    ject (la superclase de todas las clases en Java)
    @Override
    public Object clone() {
        // Inicia un bloque try-catch para manejar posibles excepciones
        try {
            // Esta línea intenta llamar al método clone() de la superclase (la clase Object). Si la clase Product y sus clases padre implementan la interfaz Cloneable, esto creará una copia superficial del objeto.
            // Copia superficial: significa que se crea un nuevo objeto y los campos primitivos se copian directamente, pero los campos que son referencias a otros objetos se copian como referencias (no se crean copias de esos objetos referenciados).
            return super.clone();
        //  Captura la excepción CloneNotSupportedException. Esta excepción se lanza si la clase Product no implementa la interfaz Cloneable.
        } catch (CloneNotSupportedException e) {
            // Si se lanza la excepción, esta línea crea una nueva instancia de Product utilizando el constructor de la clase y copia los valores de los campos id, name, y price del objeto original. Esto se llama una copia profunda, ya que se crea un objeto nuevo.
            // Copia profunda: significa que se crea un objeto nuevo y se crean copias de todos los campos, incluyendo los campos que son referencias a otros objetos.
            return new Product(this.getId(), getName(), price);
        }
    }

}
