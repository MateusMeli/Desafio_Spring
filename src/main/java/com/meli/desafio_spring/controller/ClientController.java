package com.meli.desafio_spring.controller;

import com.meli.desafio_spring.dto.ClientDto;
import com.meli.desafio_spring.model.Client;
import com.meli.desafio_spring.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
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
    public ResponseEntity<List<ClientDto>> addClients(@RequestBody List<Client> clientList) throws FileNotFoundException {
        return new ResponseEntity<>(service.addClients(clientList), HttpStatus.OK);
    }

    /**
     * Get a list of all clients
     *
     * @return Response a list of clients
     * @see <a href="http://localhost:8080/api/vi/clients">Get All clients</a>
     */
    @GetMapping("/clients")
    public ResponseEntity<List<ClientDto>> getAllClients(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String state,
            @RequestParam(required = false) Long cpf

            ) throws FileNotFoundException {

        return new ResponseEntity<>(service.getAllClients(name, state, cpf), HttpStatus.OK);
    }

}
