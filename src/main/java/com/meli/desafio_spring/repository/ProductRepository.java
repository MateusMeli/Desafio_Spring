package com.meli.desafio_spring.repository;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.meli.desafio_spring.exception.FileNotFoundException;
import com.meli.desafio_spring.exception.NotFoundException;
import com.meli.desafio_spring.model.Product;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class ProductRepository {

    private static final String DATA = "src/main/resources/product.json";

    public List<Product> addProducts(List<Product> listProduct) throws FileNotFoundException {
        ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
        ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
        List<Product> actualList = null;
        List<Product> copylist = null;

        try {
            actualList = Arrays.asList(mapper.readValue(new File(DATA), Product[].class));
            copylist = new ArrayList<>(actualList);
            copylist.addAll(listProduct);

            writer.writeValue(new File(DATA), copylist);
            return copylist;
        } catch (NotFoundException | IOException ex) {
            throw new FileNotFoundException("Data not found: " + DATA);
        }


    }

    public List<Product> getAllProducts() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return Arrays.asList
                    (mapper.readValue(new File(DATA), Product[].class));

        } catch (NotFoundException | IOException ex) {
            throw new FileNotFoundException("Data not found: " + DATA);
        }
    }
}
