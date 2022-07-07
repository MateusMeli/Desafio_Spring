package com.meli.desafio_spring.controller;

import com.meli.desafio_spring.dto.ProductDto;
import com.meli.desafio_spring.model.Product;
import com.meli.desafio_spring.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Products API Controller Class
 */
@RestController
@RequestMapping("/api/v1")
public class ProductController {
    @Autowired
    private ProductService service;

    /**
     * @param productList
     * @return Response a list of products (data transfer object)
     * @see <a href="http://localhost:8080/api/vi/insert-products-request">Add Products</a>
     * Request to create a list of products
     */
    @PostMapping("/insert-products-request")
    public ResponseEntity<List<ProductDto>> addProducts(@RequestBody List<Product> productList) {
        return new ResponseEntity<>(service.addProducts(productList), HttpStatus.OK);
    }

    /**
     * Get a list of all products
     *
     * @param category     - ?category=category - Get All Products Filtered By Category
     * @param freeShipping - ?category=categoryName&freeShipping=true - Get All Products Filtered By Category And FreeShipping
     * @param prestige     - ?freeShipping=true&prestige=**** - Get All Products Filtered By FreeShipping And Prestige
     * @param order        - ?category=categoryName&freeShipping=true&order=0 - Get All Products Filtered By Category And FreeShipping And Sorted By Name
     * @return Response a list of products
     * To add filters use the RequestParam pattern
     * @see <a href="http://localhost:8080/api/vi/products">Get All Products</a>
     */
    @GetMapping("/products")
    public ResponseEntity<List<ProductDto>> getAllProducts(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) Boolean freeShipping,
            @RequestParam(required = false) String prestige,
            @RequestParam(required = false) Integer order) {

        return new ResponseEntity<>(service.getAllProducts(category, freeShipping, prestige, order), HttpStatus.OK);
    }
}
