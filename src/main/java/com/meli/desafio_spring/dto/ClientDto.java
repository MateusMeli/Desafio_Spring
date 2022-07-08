package com.meli.desafio_spring.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ClientDto {
    private long clientId;
    private String name;
    private String state;
}