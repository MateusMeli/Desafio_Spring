package com.meli.desafio_spring.repository;

import com.fasterxml.jackson.core.PrettyPrinter;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.meli.desafio_spring.dto.ProductDto;
import com.meli.desafio_spring.model.Product;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ProductRepository {
     private final String linkFile = "src/main/resources/product.json";

     public List<ProductDto> addProducts(List<Product> listProduct) {
          ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
          ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
          List<Product> actualList = null;
          List<Product> copylist = null;

          try {
               actualList = Arrays.asList(mapper.readValue(new File(linkFile), Product[].class));
               copylist = new ArrayList<>(actualList);
               copylist.addAll(listProduct);

               writer.writeValue(new File(linkFile), copylist);
          } catch (Exception ex) {

          }

          return copylist.stream().map( p -> ProductDto
                  .builder()
                  .productid(p.getProductId())
                  .name(p.getName())
                  .quantity(p.getQuantity()).build())
                  .collect(Collectors.toList());

     }

     public List<ProductDto> getAllProducts() {
          List<ProductDto> list = new ArrayList<>();
          return list;
     }
}
