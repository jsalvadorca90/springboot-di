package com.udemy.springboot.di.app.springboot_di.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    
    private Long id;
    private String name;
    private Long price;

}
