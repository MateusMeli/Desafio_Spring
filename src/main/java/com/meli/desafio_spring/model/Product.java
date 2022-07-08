package com.meli.desafio_spring.repository.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    private long productId;
    private String name;
    private String category;
    private String brand;
    private double price;
    private int quantity;
    private Boolean freeShipping;
    private String prestige;

}
