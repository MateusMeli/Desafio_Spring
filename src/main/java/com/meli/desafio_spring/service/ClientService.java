package com.meli.desafio_spring.service;

import com.meli.desafio_spring.dto.ClientDto;
import com.meli.desafio_spring.dto.ProductDto;
import com.meli.desafio_spring.model.Client;

import java.util.List;

public interface ClientService {
    List<ClientDto> addClients(List<Client> listClient);

    List<ClientDto> getAllClients();

}
