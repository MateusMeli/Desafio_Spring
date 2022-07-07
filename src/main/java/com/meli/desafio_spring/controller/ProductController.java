package com.meli.desafio_spring.controller;

import com.meli.desafio_spring.dto.ProductDto;
import com.meli.desafio_spring.model.Product;
import com.meli.desafio_spring.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<List<ProductDto>> getAllProducts(
            @RequestParam Optional<String> category,
            @RequestParam Optional<Boolean> freeShipping,
            @RequestParam Optional<String> prestige) {


        return new ResponseEntity(service.getAllProducts(category, freeShipping, prestige), HttpStatus.OK);
    }


}
