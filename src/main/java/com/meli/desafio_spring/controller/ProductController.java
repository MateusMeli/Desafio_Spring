package com.meli.desafio_spring.controller;

import com.meli.desafio_spring.dto.ProductDto;
import com.meli.desafio_spring.model.ProductPurchaseRequest;
import com.meli.desafio_spring.model.Product;
import com.meli.desafio_spring.model.Ticket;
import com.meli.desafio_spring.service.ProductService;
import com.meli.desafio_spring.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ProductController {
    @Autowired
    private ProductService serviceProduct;

    @Autowired
    private TicketService serviceTicket;

    @PostMapping("/insert-products-request")
    public ResponseEntity<List<ProductDto>> addProducts(@RequestBody List<Product> productList) {
        return new ResponseEntity<>(serviceProduct.addProducts(productList), HttpStatus.OK);
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductDto>> getAllProducts(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) Boolean freeShipping,
            @RequestParam(required = false) String prestige,
            @RequestParam(required = false) Integer order) {

        return new ResponseEntity<List<ProductDto>>(serviceProduct.getAllProducts(category, freeShipping, prestige, order), HttpStatus.OK);
    }

    @PostMapping("/purchase-request")
    public ResponseEntity<Ticket> purchaseRequest(@RequestBody ProductPurchaseRequest purchaseObject) {

        return new ResponseEntity<Ticket>(serviceTicket.purchaseRequest(purchaseObject), HttpStatus.OK);
    }
}
