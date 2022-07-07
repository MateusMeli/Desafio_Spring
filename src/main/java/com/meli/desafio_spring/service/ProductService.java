package com.meli.desafio_spring.service;

import com.meli.desafio_spring.dto.ProductDto;
import com.meli.desafio_spring.model.Product;

import java.util.List;

public interface ProductService {
    List<ProductDto> addProducts(List<Product> listProduct);
    List<ProductDto> getAllProducts();
}
