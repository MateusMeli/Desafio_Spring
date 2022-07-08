package com.meli.desafio_spring.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ticket {
    private long id;
    private List<Product> listaProdutos;
    private double total;
}
