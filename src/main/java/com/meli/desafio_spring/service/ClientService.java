package com.meli.desafio_spring.service;

import com.meli.desafio_spring.dto.ClientDto;
import com.meli.desafio_spring.model.Client;
import com.meli.desafio_spring.model.Product;

import java.io.FileNotFoundException;
import java.util.List;

public interface ClientService {
    List<ClientDto> addClients(List<Client> listClient) throws FileNotFoundException;

    List<ClientDto> getAllClients(
            String name,
            String state,
            Long cpf
    ) throws FileNotFoundException;

    List<Client> sort(List<Client> list, Integer order);


}
