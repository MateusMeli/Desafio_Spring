package com.meli.desafio_spring.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ProductDto {
    private long productid;
    private String name;
    private int quantity;
}
