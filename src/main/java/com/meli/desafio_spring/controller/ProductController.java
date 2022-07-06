package com.meli.desafio_spring.controller;

@RestController
@RequestMapping('/api/v1')
public class ProductController {

    @GetMapping('/hello')
    public String getHello() {
        return 'Ola!'
    }
}
