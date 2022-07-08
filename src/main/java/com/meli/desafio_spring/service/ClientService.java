package com.meli.desafio_spring.service;

import com.meli.desafio_spring.dto.ClientDto;
import com.meli.desafio_spring.model.Client;

import java.io.FileNotFoundException;
import java.util.List;

public interface ClientService {
    List<ClientDto> addClients(List<Client> listClient) throws FileNotFoundException;

    List<ClientDto> getAllClients() throws FileNotFoundException;

}
