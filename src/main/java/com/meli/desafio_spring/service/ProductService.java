package com.meli.desafio_spring.service;

import com.meli.desafio_spring.dto.ProductDto;
import com.meli.desafio_spring.model.Product;
import com.meli.desafio_spring.model.Ticket;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<ProductDto> addProducts(List<Product> listProduct);

    List<ProductDto> getAllProducts(
            String category,
            Boolean freeShipping,
            String prestige,
            Integer order
    );

    List<Product> sort(List<Product> list, Integer order);
}
