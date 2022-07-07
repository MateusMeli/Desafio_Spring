package com.meli.desafio_spring.controller;

import com.meli.desafio_spring.dto.ProductDto;
import com.meli.desafio_spring.model.Product;
import com.meli.desafio_spring.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ProductController {
    @Autowired
    private ProductService service;

    @PostMapping("/insert-articles-request")
    public ResponseEntity<List<ProductDto>> addProducts(@RequestBody List<Product> productList) {
        return new ResponseEntity(service.addProducts(productList), HttpStatus.OK);
    }

    @GetMapping("/articles")
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        return new ResponseEntity(service.getAllProducts(), HttpStatus.OK);
    }

    @GetMapping("/articles")
    public ResponseEntity<List<ProductDto>> getByFreeShippingAndCategory(@RequestParam String category, @RequestParam boolean freeShipping) {
        return new ResponseEntity<List<ProductDto>>(service.getByFreeShippingAndCategory(category, freeShipping), HttpStatus.OK);
    }
}
