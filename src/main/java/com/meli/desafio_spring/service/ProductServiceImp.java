package com.meli.desafio_spring.service;

import com.meli.desafio_spring.dto.ProductDto;
import com.meli.desafio_spring.model.Product;
import com.meli.desafio_spring.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImp implements ProductService{
    @Autowired
    ProductRepository repo;

    @Override
    public List<ProductDto> addProducts(List<Product> listProduct) {
        return repo.addProducts(listProduct);
    }
}
