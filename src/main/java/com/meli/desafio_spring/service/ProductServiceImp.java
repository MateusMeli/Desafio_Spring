package com.meli.desafio_spring.service;

import com.meli.desafio_spring.dto.ProductDto;
import com.meli.desafio_spring.model.Product;
import com.meli.desafio_spring.model.Ticket;
import com.meli.desafio_spring.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
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
            String category,
            Boolean freeShipping,
            String prestige,
            Integer order) {

        List<Product> listProduct = repo.getAllProducts();

        listProduct = productFilters(category, freeShipping, prestige, order, listProduct);

        return listProduct.stream()
                .map(p -> ProductDto
                        .builder()
                        .productid(p.getProductId())
                        .name(p.getName())
                        .quantity(p.getQuantity())
                        .price(p.getPrice())
                        .build())
                .collect(Collectors.toList());

    }

    private List<Product> productFilters(
            String category,
            Boolean freeShipping,
            String prestige,
            Integer order,
            List<Product> listProduct
    ) {

        List<Product> productListFilter = new ArrayList<>();

        productListFilter.addAll(listProduct);

        if (category != null) {
            productListFilter = productListFilter
                    .stream()
                    .filter(c -> c.getCategory().equals(category))
                    .collect(Collectors.toList());
        }
        if (freeShipping != null) {
            productListFilter = productListFilter
                    .stream()
                    .filter(f -> f.getFreeShipping().equals(freeShipping))
                    .collect(Collectors.toList());
        }
        if (prestige != null) {
            productListFilter = productListFilter
                    .stream()
                    .filter(p -> p.getPrestige().equals(prestige))
                    .collect(Collectors.toList());
        }
        if (order != null) {
            productListFilter = sort(productListFilter, order);
        }

        return productListFilter;
    }

    public List<Product> sort(List<Product> list, Integer order) {
        switch (order) {
            case 0:
                list.sort(Comparator.comparing(Product::getName));
                break;
            case 1:
                list.sort(Comparator.comparing(Product::getName).reversed());
                break;
            case 2:
                list.sort(Comparator.comparing(Product::getPrice));
                break;
            case 3:
                list.sort(Comparator.comparing(Product::getPrice).reversed());
                break;
        }
        return list;
    }

}
