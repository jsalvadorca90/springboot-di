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

    @Override
    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            return new Product(this.getId(), getName(), price);
        }
    }

}
