package com.meli.desafio_spring.service;

import com.meli.desafio_spring.dto.ProductDto;
import com.meli.desafio_spring.model.Product;
import com.meli.desafio_spring.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImp implements ProductService {
    @Autowired
    ProductRepository repo;

    @Override
    public List<ProductDto> addProducts(List<Product> listProduct) {

        return repo.addProducts(listProduct).stream().map(p -> ProductDto
                        .builder()
                        .productid(p.getProductId())
                        .name(p.getName())
                        .quantity(p.getQuantity()).build())
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> getAllProducts(
            Optional<String> category,
            Optional<Boolean> freeShipping,
            Optional<String> prestige) {

        List<Product> listProduct = repo.getAllProducts();

        listProduct = productFilters(category, freeShipping, prestige, listProduct);

        return listProduct.stream()
                .map(p -> ProductDto
                        .builder()
                        .productid(p.getProductId())
                        .name(p.getName())
                        .quantity(p.getQuantity()).build())
                .collect(Collectors.toList());

    }

    private List<Product> productFilters(
            Optional<String> category,
            Optional<Boolean> freeShipping,
            Optional<String> prestige,
            List<Product> listProduct
    ) {

        List<Product> productListFilter = new ArrayList<>();

        productListFilter.addAll(listProduct);

        if (category.isPresent()) {
            productListFilter = productListFilter
                    .stream()
                    .filter(c -> c.getCategory().equals(category.get()))
                    .collect(Collectors.toList());
        }
        if (freeShipping.isPresent()) {
            productListFilter = productListFilter
                    .stream()
                    .filter(f -> f.getFreeShipping().equals(freeShipping.get()))
                    .collect(Collectors.toList());
        }
        if (prestige.isPresent()) {
            productListFilter = productListFilter
                    .stream()
                    .filter(p -> p.getPrestige().equals(prestige.get()))
                    .collect(Collectors.toList());
        }
        return productListFilter;
    }


}
