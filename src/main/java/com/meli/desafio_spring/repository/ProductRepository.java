package com.meli.desafio_spring.repository;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.meli.desafio_spring.dto.ProductDtoPurchase;
import com.meli.desafio_spring.model.Product;
import com.meli.desafio_spring.model.ProductPurchaseRequest;
import com.meli.desafio_spring.model.Ticket;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ProductRepository {

    private static final String DATA = "src/main/resources/product.json";

    public List<Product> addProducts(List<Product> listProduct) {
        ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
        ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
        List<Product> actualList = null;
        List<Product> copylist = null;

        try {
            actualList = Arrays.asList(mapper.readValue(new File(DATA), Product[].class));
            copylist = new ArrayList<>(actualList);
            copylist.addAll(listProduct);

            writer.writeValue(new File(DATA), copylist);
        } catch (Exception ex) {

        }

        return copylist;
    }

    public List<Product> getAllProducts() {
        ObjectMapper mapper = new ObjectMapper();
        List<Product> lista = new ArrayList<>();
        try {
            lista.addAll(Arrays.asList
                    (mapper.readValue(new File(DATA), Product[].class)));

        } catch (Exception ex) {

        }
        return lista;
    }

    public void updateProductStock(ProductPurchaseRequest productPurchaseRequest) {
        ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
        ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
        List<Product> actualList = null;
        List<Product> copylist = null;

        try {
            actualList = Arrays.asList(mapper.readValue(new File(DATA), Product[].class));
            for(ProductDtoPurchase productDtoPurchase : productPurchaseRequest.getProductPurchaseRequest()) {
                copylist = actualList.stream().map(p -> {
                    if (p.getProductId() == productDtoPurchase.getProductId()) {
                        p.setQuantity(p.getQuantity() - productDtoPurchase.getQuantity());

                    }
                    return p;
                }).collect(Collectors.toList());
            }

            writer.writeValue(new File(DATA), copylist);
        } catch (Exception ex) {

        }
    }
}
