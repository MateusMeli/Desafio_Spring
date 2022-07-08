package com.meli.desafio_spring.controller;

import com.meli.desafio_spring.dto.ClientDto;
import com.meli.desafio_spring.model.Client;
import com.meli.desafio_spring.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Client API Controller Class
 */
@RestController
@RequestMapping("/api/v1")
public class ClientController {
    @Autowired
    private ClientService service;

    /**
     * @param clientList
     * @return Response a list of clients (data transfer object)
     * @see <a href="http://localhost:8080/api/vi/insert-client-request">Add Clients</a>
     * Request to create a list of clients
     */
    @PostMapping("/insert-clients-request")
    public ResponseEntity<List<ClientDto>> addClients(@RequestBody List<Client> clientList) {
        return new ResponseEntity<>(service.addClients(clientList), HttpStatus.OK);
    }

}
