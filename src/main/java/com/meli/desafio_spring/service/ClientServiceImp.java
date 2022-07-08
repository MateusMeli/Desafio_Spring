package com.meli.desafio_spring.service;

import com.meli.desafio_spring.dto.ClientDto;
import com.meli.desafio_spring.model.Client;
import com.meli.desafio_spring.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientServiceImp implements ClientService {
    @Autowired
    ClientRepository repo;

    @Override
    public List<ClientDto> addClients(List<Client> listClient) {

        return repo.addClients(listClient).stream().map(c -> ClientDto
                        .builder()
                        .clientId(c.getClientId())
                        .name(c.getName())
                        .state(c.getState()).build())
                .collect(Collectors.toList());
    }
}
