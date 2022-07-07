package com.meli.desafio_spring.service;

import com.meli.desafio_spring.dto.ProductDto;
import com.meli.desafio_spring.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<ProductDto> addProducts(List<Product> listProduct);

    List<ProductDto> getAllProducts(
            Optional<String> category,
            Optional<Boolean> freeShipping,
            Optional<String> prestige
    );

    List<ProductDto> getAllProducts(Integer order);

    List<ProductDto> sort(List<ProductDto> list, Integer order);
}
