package com.meli.desafio_spring.controller;

import com.meli.desafio_spring.dto.ProductDto;
import com.meli.desafio_spring.model.Product;
import com.meli.desafio_spring.model.ProductPurchaseRequest;
import com.meli.desafio_spring.model.Ticket;
import com.meli.desafio_spring.service.ProductService;
import com.meli.desafio_spring.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.util.List;

/**
 * Products API Controller Class
 */
@RestController
@RequestMapping("/api/v1")
public class ProductController {
    @Autowired
    private ProductService serviceProduct;

    @Autowired
    private TicketService serviceTicket;

    /**
     * @param productList list of Products
     * @return Response a list of products (data transfer object)
     * @see <a href="http://localhost:8080/api/vi/insert-products-request">Add Products</a>
     * @throws FileNotFoundException When not exits file
     * Request to create a list of products
     */
    @PostMapping("/insert-products-request")
    public ResponseEntity<List<ProductDto>> addProducts(@RequestBody List<Product> productList) throws FileNotFoundException {
        return new ResponseEntity<>(serviceProduct.addProducts(productList), HttpStatus.OK);
    }

    /**
     * Get a list of all products
     *
     * @param category     - ?category=category - Get All Products Filtered By Category
     * @param freeShipping - ?freeShipping=true - Get All Products Filtered By Category And FreeShipping
     * @param prestige     - ?prestige=**** - Get All Products Filtered By FreeShipping And Prestige
     * @param order        - ?order=0 - Get All Products Filtered By Category And FreeShipping And Sorted By Name
     * @return Response a list of products
     * @throws FileNotFoundException When not exits file
     * To add filters use the RequestParam pattern
     * @see <a href="http://localhost:8080/api/vi/products">Get All Products</a>
     */
    @GetMapping("/products")
    public ResponseEntity<List<ProductDto>> getAllProducts(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) Boolean freeShipping,
            @RequestParam(required = false) String prestige,
            @RequestParam(required = false) Integer order) throws FileNotFoundException {


        return new ResponseEntity<>(serviceProduct.getAllProducts(category, freeShipping, prestige, order), HttpStatus.OK);
    }

    /**
     * Purchase a list of products
     *
     * @param purchaseObject - Receives an order that has a list of wanted products.
     * @return A list of the order with the product information and the total of all products added together.
     * @throws FileNotFoundException When not exits file
     * @see <a href="http://localhost:8080/api/vi/purchase-request">Purchase a list of products</a>
     */
    @PostMapping("/purchase-request")
    public ResponseEntity<Ticket> purchaseRequest(@RequestBody ProductPurchaseRequest purchaseObject) throws FileNotFoundException {

        return new ResponseEntity<>(serviceTicket.purchaseRequest(purchaseObject), HttpStatus.OK);
    }

}
